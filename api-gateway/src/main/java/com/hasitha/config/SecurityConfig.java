package com.hasitha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
/*This is because since  spring cloud gateway is based of web flux projrct.
Unless we may add @WebSecurity annotaion
*/
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity){
        serverHttpSecurity.csrf().disable().
                /*Below means skip authentication for eureka/** url pattern.
                Since above url pattern is related to loading css styles/ javascript /images of eureka server.
                And then authenticate all other urls*/
                authorizeExchange(exchange->exchange.pathMatchers("/eureka/**").permitAll()
                        //.pathMatchers("/api/product").hasRole("user")
                        //.pathMatchers("/api/product/**").permitAll()
                        .anyExchange().authenticated()).
                oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
        return serverHttpSecurity.build();

    }
}
