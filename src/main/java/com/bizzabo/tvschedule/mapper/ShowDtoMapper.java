package com.bizzabo.tvschedule.mapper;

import com.bizzabo.tvschedule.client.model.tvmaze.Actor;
import com.bizzabo.tvschedule.client.model.tvmaze.Show;
import com.bizzabo.tvschedule.dto.ShowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ShowDtoMapper {

    private final ActorDtoMapper actorDtoMapper;

    public ShowDto map(Show show) {
        return ShowDto.builder()
                .id(show.getId())
                .name(show.getName())
                .imageUrl(Objects.nonNull(show.getImage())
                        ? show.getImage().getOriginal()
                        : null)
                .cast(Objects.nonNull(show.getEmbedded())
                        ? show.getEmbedded().getCast().stream()
                                .map(Actor::getPerson)
                                .map(actorDtoMapper::map)
                                .toList()
                        : null)
                .build();
    }
}
