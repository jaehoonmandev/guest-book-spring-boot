package com.jaehoonman.guestbookspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    //security 의 단반향 Encode 기능을 PermitCode 암호화에 사용한다.
    @Bean
    public PasswordEncoder permitCondeEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(
                        (csrf) -> csrf.disable()
                )
                .authorizeHttpRequests(
                        (authz) -> authz
                                .requestMatchers(HttpMethod.PUT, "/guestbook/").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/guestbook/").authenticated()
                                .anyRequest().permitAll()

                );
        return http.build();
    }

}
