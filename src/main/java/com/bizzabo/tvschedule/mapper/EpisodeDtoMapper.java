package com.bizzabo.tvschedule.mapper;

import com.bizzabo.tvschedule.client.model.tvmaze.Episode;
import com.bizzabo.tvschedule.dto.EpisodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EpisodeDtoMapper {

    private final ShowDtoMapper showDtoMapper;

    public EpisodeDto map(Episode episode, long showId) {
        return EpisodeDto.builder()
                .id(episode.getId())
                .name(episode.getName())
                .season(episode.getSeason())
                .number(episode.getNumber())
                .airDate(episode.getAirDate())
                .showId(showId)
                .build();
    }
}
