package com.bizzabo.tvschedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeDto {
    private Long id;
    private long showId;
    private String name;
    private long season;
    private long number;
    private String airDate;
}
