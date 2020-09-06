package com.github.lotus.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Created by hocgin on 2020/1/6.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@EnableWebFluxSecurity
public class SecurityConfig {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/**").authorizeRequests()
//            .antMatchers("/", "/login**").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .oauth2Login();
//    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
            .pathMatchers("/actuator/**").permitAll()
            .pathMatchers("/", "/login**").permitAll()
            .anyExchange().authenticated();

        http.oauth2Login();
//        http.oauth2ResourceServer().jwt();

        return http.build();
    }
}
