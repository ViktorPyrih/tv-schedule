package com.bizzabo.tvschedule.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowDto {

    private Long id;
    private String name;
    private String imageUrl;
    private List<ActorDto> cast;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowDto showDto)) return false;
        return Objects.equals(id, showDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
