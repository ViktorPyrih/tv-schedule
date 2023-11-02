package com.bizzabo.tvschedule.client.model.tvmaze;

import lombok.Data;

import java.util.List;

@Data
public class Embedded {
    private List<Actor> cast;
    private Show show;
}
