package com.bizzabo.tvschedule.controller;

import com.bizzabo.tvschedule.dto.ShowDto;
import com.bizzabo.tvschedule.service.SuggestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/me/schedule/suggestions")
@RestController
public class MySuggestionsController {

    private final SuggestionsService suggestionsService;

    @GetMapping
    public List<ShowDto> getSuggestions() {
        return suggestionsService.getSuggestions();
    }
}
