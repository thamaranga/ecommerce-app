package com.hasitha.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/*Here we are using only bacis spring security.
* We are not using anything related to OAuth2 here.*/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication().
                passwordEncoder(NoOpPasswordEncoder.getInstance()).
                withUser("eureka")
                .password("password")
                .authorities("USER");

    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
       httpSecurity.csrf().disable().authorizeRequests().anyRequest()
               .authenticated()
               .and()
               .httpBasic();

    }


}
