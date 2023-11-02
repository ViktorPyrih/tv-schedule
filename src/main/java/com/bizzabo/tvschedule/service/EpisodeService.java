package com.bizzabo.tvschedule.service;

import com.bizzabo.tvschedule.client.TvMazeClient;
import com.bizzabo.tvschedule.dto.EpisodeDto;
import com.bizzabo.tvschedule.entity.Episode;
import com.bizzabo.tvschedule.entity.Show;
import com.bizzabo.tvschedule.mapper.EpisodeDtoMapper;
import com.bizzabo.tvschedule.repository.EpisodeRepository;
import com.bizzabo.tvschedule.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EpisodeService {

    private final ShowRepository showRepository;
    private final EpisodeRepository episodeRepository;
    private final TvMazeClient tvMazeClient;
    private final EpisodeDtoMapper episodeDtoMapper;

    public void saveToWatched(long episodeId) {
        long showId = tvMazeClient.getEpisodeById(episodeId).getEmbedded().getShow().getId();
        Show show = showRepository.findByOriginalShowId(showId).orElseThrow();
        episodeRepository.save(new Episode()
                .show(show)
                .originalEpisodeId(episodeId));
    }
    
    public List<EpisodeDto> getNextEpisodes() {
        List<Long> scheduleShowIds = showRepository.findAll().stream()
                .map(Show::originalShowId)
                .toList();
        Map<Long, Long> maxEpisodeIdPerShowId = showRepository.findMaxEpisodeIdPerShowId().stream()
                .collect(Collectors.toMap(tuple -> tuple.get(0, Long.class), tuple -> tuple.get(1, Long.class)));

        return scheduleShowIds.stream()
                .map(id -> {
                    List<com.bizzabo.tvschedule.client.model.tvmaze.Episode> episodes = tvMazeClient.getShowEpisodes(id);
                    return Map.entry(episodes.stream()
                            .filter(episode -> episode.getId() > Optional.ofNullable(maxEpisodeIdPerShowId.get(id))
                                    .orElse(Long.MIN_VALUE))
                            .findFirst()
                            .orElse(episodes.get(episodes.size() - 1)), id);
                })
                .map(entry -> episodeDtoMapper.map(entry.getKey(), entry.getValue()))
                .toList();
    }
}
