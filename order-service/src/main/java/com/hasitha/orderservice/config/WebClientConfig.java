package com.hasitha.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Bean
    /*When there are multiple instances of client service, with  below
    annotation webclient can decide which instance need to call
    */
    @LoadBalanced
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }
}
