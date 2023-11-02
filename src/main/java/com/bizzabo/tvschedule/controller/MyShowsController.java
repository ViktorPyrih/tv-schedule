package com.bizzabo.tvschedule.controller;

import com.bizzabo.tvschedule.dto.ShowDto;
import com.bizzabo.tvschedule.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/me/schedule/shows")
@RestController
public class MyShowsController {

    private final ShowService showService;

    @PostMapping("/{showId}")
    public ResponseEntity<Void> addToSchedule(@PathVariable long showId) {
        showService.saveToSchedule(showId);
        return ResponseEntity.created(URI.create("/api/v1/me/schedule/shows/%d".formatted(showId)))
                .build();
    }

    @DeleteMapping("/{showId}")
    public ResponseEntity<Void> deleteFromSchedule(@PathVariable long showId) {
        showService.deleteFromSchedule(showId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<ShowDto> getAllFromSchedule(@RequestParam(required = false, defaultValue = "false") boolean unwatched) {
        return showService.getAllFromSchedule(unwatched);
    }
}
