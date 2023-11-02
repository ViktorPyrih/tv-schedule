package com.bizzabo.tvschedule.service;

import com.bizzabo.tvschedule.client.TvMazeClient;
import com.bizzabo.tvschedule.dto.ShowDto;
import com.bizzabo.tvschedule.entity.Episode;
import com.bizzabo.tvschedule.entity.Show;
import com.bizzabo.tvschedule.mapper.ShowDtoMapper;
import com.bizzabo.tvschedule.repository.EpisodeRepository;
import com.bizzabo.tvschedule.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final EpisodeRepository episodeRepository;
    private final TvMazeClient tvMazeClient;
    private final ShowDtoMapper showDtoMapper;

    public void saveToSchedule(long showId) {
        showRepository.save(new Show().originalShowId(showId));
    }

    public void deleteFromSchedule(long id) {
        showRepository.deleteById(id);
    }

    public List<ShowDto> getAllFromSchedule(boolean unwatched) {
        Stream<Long> showIdsStream = showRepository.findAll().stream().map(Show::originalShowId);
        if (unwatched) {
            Set<Long> watchedEpisodes = episodeRepository.findAll().stream()
                    .map(Episode::originalEpisodeId)
                    .collect(Collectors.toSet());
            showIdsStream = showIdsStream.map(showId -> Map.entry(showId, tvMazeClient.getShowEpisodes(showId)))
                    .filter(entry -> !entry.getValue().stream()
                            .map(com.bizzabo.tvschedule.client.model.tvmaze.Episode::getId)
                            .allMatch(watchedEpisodes::contains))
                    .map(Map.Entry::getKey);
        }

        return showIdsStream
                .map(tvMazeClient::getShowById)
                .map(showDtoMapper::map)
                .toList();
    }
}
