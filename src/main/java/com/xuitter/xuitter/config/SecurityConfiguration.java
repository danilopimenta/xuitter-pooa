package com.xuitter.xuitter.config;

import com.xuitter.xuitter.config.filter.BearerTokenAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final BearerTokenAuthFilter bearerTokenAuthFilter;

    public SecurityConfiguration(BearerTokenAuthFilter bearerTokenAuthFilter) {
        this.bearerTokenAuthFilter = bearerTokenAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/auth/register", "/auth/login", "/").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(bearerTokenAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
