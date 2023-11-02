package com.bizzabo.tvschedule.client.model.tvmaze;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Episode {
    private long id;
    private String name;
    private long season;
    private long number;
    @JsonProperty("airdate")
    private String airDate;
    @JsonProperty("_embedded")
    private Embedded embedded;
}
