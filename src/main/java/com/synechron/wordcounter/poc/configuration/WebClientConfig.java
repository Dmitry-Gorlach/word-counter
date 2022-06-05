package com.synechron.wordcounter.poc.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientConfig {

    /**
     * An example of webClient filter
     */
    private final ExchangeFilterFunction filterFunction = (clientRequest, nextFilter) -> {
        log.info("WebClient filter executed.");
        return nextFilter.exchange(clientRequest);
    };

    /**
     * Here we can add a request filter to the filter chain
     * @return webClient
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder().filter(filterFunction).build();
    }


}
