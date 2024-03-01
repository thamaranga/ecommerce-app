package com.hasitha.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Bean
    /*When there are multiple instances of client service(Ex: inventory-service), with  below
    annotation webclient can decide which instance need to call. So webclient will
    try to call one instance of the client. If it is working then ok. Unless call to next
    instance of the client etc...This  multiple instancelist about the client service is returned
    by the discovery server when order service  do the service discovery for inventory-service
    */
    @LoadBalanced
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }
}
