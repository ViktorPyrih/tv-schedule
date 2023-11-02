package com.bizzabo.tvschedule.client.model.tvmaze;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CastCredit {
    @JsonProperty("_embedded")
    private Embedded embedded;
}
