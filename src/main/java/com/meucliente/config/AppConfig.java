package com.meucliente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    protected WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}