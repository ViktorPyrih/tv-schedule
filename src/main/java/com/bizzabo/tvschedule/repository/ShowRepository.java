package com.bizzabo.tvschedule.repository;

import com.bizzabo.tvschedule.entity.Show;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    Optional<Show> findByOriginalShowId(long showId);

    @Query("""
        SELECT
            show.originalShowId, max(episode.originalEpisodeId)
        FROM
            Show show
        JOIN
            Episode episode
        ON
            show = episode.show
        GROUP BY
            show.originalShowId
    """)
    List<Tuple> findMaxEpisodeIdPerShowId();
}
