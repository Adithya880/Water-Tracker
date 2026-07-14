package com.adithya.watertracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/login.html",
                                "/signup.html",
                                "/dashboard.html",
                                "/history.html",
                                "/compare.html",
                                "/reminder.html",

                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/icons/**",
                                "/fonts/**",

                                "/api/auth/**"
                        ).permitAll()

                        .anyRequest().permitAll()
                )

                .formLogin(form -> form.disable())

                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }
}