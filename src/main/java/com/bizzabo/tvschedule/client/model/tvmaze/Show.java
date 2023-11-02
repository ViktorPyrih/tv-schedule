package com.bizzabo.tvschedule.client.model.tvmaze;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Show {
    private long id;
    private String name;
    private Image image;
    @JsonProperty("_embedded")
    private Embedded embedded;
}
