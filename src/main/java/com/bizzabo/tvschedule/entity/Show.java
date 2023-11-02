package com.bizzabo.tvschedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@Entity
public class Show {

    @Id
    @GeneratedValue
    private Long id;

    private long originalShowId;
}
