package com.cloud.ecommerce.resourceserver.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
@Configuration
public class ProjectSecurityConfig {
    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }));



        http.authorizeHttpRequests((authorize) ->
                authorize
                        .requestMatchers(
                            "/api/shop/categories",
                            "/api/shop/brands",
                            "/api/shop/products",
                            "/api/shop/products/**",
                            "/api/basket",
                            "/api/basket/**",
                            "/api/basket/allBaskets"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable);
        //.requestMatchers("/api/shop/categories").authenticated());
        // .httpBasic(Customizer.withDefaults())
        //.formLogin(Customizer.withDefaults());
        return http.build();
    }
}
