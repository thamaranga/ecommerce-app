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
                authorizeExchange(exchznge->exchznge.pathMatchers("/eureka/**").permitAll()
                        .anyExchange().authenticated()).
                oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
        return serverHttpSecurity.build();

    }
}
