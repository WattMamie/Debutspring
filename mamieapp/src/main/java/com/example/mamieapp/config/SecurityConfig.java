package com.example.mamieapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Active la configuration de sécurité web de Spring
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. DÉSACTIVER CSRF : ESSENTIEL pour les requêtes POST/PUT/DELETE dans une API REST
                .csrf(AbstractHttpConfigurer::disable)

                // 2. HTTP Basic : S'assure que Basic Auth fonctionne pour l'authentification
                .httpBasic(Customizer.withDefaults())

                // 3. Autorisation : Protège toutes les URLs
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated() // Exige une authentification pour toutes les requêtes
                );

        return http.build();
    }
}