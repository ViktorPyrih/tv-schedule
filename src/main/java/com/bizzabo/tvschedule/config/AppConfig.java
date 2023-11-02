package com.bizzabo.tvschedule.config;

import com.bizzabo.tvschedule.client.TvMazeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AppConfig {

    @Value("${tv.maze.base.url}")
    private String tvMazeBaseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(tvMazeBaseUrl).build();
    }

    @Bean
    public TvMazeClient tvMazeClient(WebClient webClient) {
        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                .build().createClient(TvMazeClient.class);
    }
}
