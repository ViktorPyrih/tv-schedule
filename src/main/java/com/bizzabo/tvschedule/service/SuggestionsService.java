package com.bizzabo.tvschedule.service;

import com.bizzabo.tvschedule.client.TvMazeClient;
import com.bizzabo.tvschedule.client.model.tvmaze.Actor;
import com.bizzabo.tvschedule.client.model.tvmaze.CastCredit;
import com.bizzabo.tvschedule.client.model.tvmaze.Embedded;
import com.bizzabo.tvschedule.client.model.tvmaze.Person;
import com.bizzabo.tvschedule.dto.ShowDto;
import com.bizzabo.tvschedule.entity.Show;
import com.bizzabo.tvschedule.mapper.ShowDtoMapper;
import com.bizzabo.tvschedule.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SuggestionsService {

    private final ShowRepository showRepository;
    private final TvMazeClient tvMazeClient;
    private final ShowDtoMapper showDtoMapper;

    public List<ShowDto> getSuggestions() {
        Set<Long> showIds = showRepository.findAll().stream()
                .map(Show::originalShowId)
                .collect(Collectors.toSet());

        return showIds.stream()
                .map(tvMazeClient::getCastById)
                .flatMap(cast -> cast.stream()
                        .map(Actor::getPerson)
                        .map(Person::getId))
                .distinct()
                .map(tvMazeClient::getCastCreditsByPersonId)
                .flatMap(List::stream)
                .map(CastCredit::getEmbedded)
                .map(Embedded::getShow)
                .distinct()
                .filter(show -> !showIds.contains(show.getId()))
                .map(showDtoMapper::map)
                .toList();
    }
}
