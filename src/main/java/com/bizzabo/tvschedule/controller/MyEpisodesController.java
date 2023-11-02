package com.bizzabo.tvschedule.controller;

import com.bizzabo.tvschedule.dto.EpisodeDto;
import com.bizzabo.tvschedule.service.EpisodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/me/schedule/episodes")
@RestController
public class MyEpisodesController {

    private final EpisodeService episodeService;

    @PostMapping("/{episodeId}")
    public ResponseEntity<Void> addToWatched(@PathVariable long episodeId) {
        episodeService.saveToWatched(episodeId);
        return ResponseEntity.created(URI.create("/api/v1/me/schedule/watched-episodes/%d".formatted(episodeId)))
                .build();
    }

    @GetMapping("/next")
    public List<EpisodeDto> getNextEpisodes() {
        return episodeService.getNextEpisodes();
    }
}
