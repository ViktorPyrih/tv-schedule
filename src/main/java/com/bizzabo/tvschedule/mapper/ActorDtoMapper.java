package com.bizzabo.tvschedule.mapper;

import com.bizzabo.tvschedule.client.model.tvmaze.Person;
import com.bizzabo.tvschedule.dto.ActorDto;
import org.springframework.stereotype.Component;

@Component
public class ActorDtoMapper {

    public ActorDto map(Person person) {
        return ActorDto.builder()
                .id(person.getId())
                .name(person.getName())
                .imageUrl(person.getImage().getOriginal())
                .build();
    }
}
