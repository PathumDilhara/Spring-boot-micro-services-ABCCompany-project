package com.example.userorder.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OrderServiceWebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClient() {
        return WebClient.builder();
    }

    @Bean
    public WebClient inventoryWebClient(){
        return webClient().baseUrl("http://Inventory/api/v1").build();
    }

    @Bean
    public WebClient productWebClient(){
        return webClient().baseUrl("http://Product/api/v1").build();
    }
}
