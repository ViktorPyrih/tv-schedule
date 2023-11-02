package com.bizzabo.tvschedule.client;

import com.bizzabo.tvschedule.client.model.tvmaze.Actor;
import com.bizzabo.tvschedule.client.model.tvmaze.CastCredit;
import com.bizzabo.tvschedule.client.model.tvmaze.Episode;
import com.bizzabo.tvschedule.client.model.tvmaze.Show;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface TvMazeClient {

    @GetExchange("/shows/{showId}?embed=cast")
    Show getShowById(@PathVariable long showId);

    @GetExchange("/episodes/{episodeId}?embed=show")
    Episode getEpisodeById(@PathVariable long episodeId);

    @GetExchange("/shows/{showId}/episodes")
    List<Episode> getShowEpisodes(@PathVariable long showId);

    @GetExchange("/shows/{showId}/cast")
    List<Actor> getCastById(@PathVariable long showId);

    @GetExchange("/people/{personId}/castcredits?embed=show")
    List<CastCredit> getCastCreditsByPersonId(@PathVariable long personId);
}
