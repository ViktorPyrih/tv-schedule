package com.bizzabo.tvschedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@Entity
public class Episode {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Show show;

    private long originalEpisodeId;
}
