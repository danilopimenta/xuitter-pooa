package com.xuitter.xuitter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/public/**").permitAll() // Rotas públicas
                                .anyRequest().authenticated() // Qualquer outra rota exige autenticação
                )
                .formLogin(formLogin -> // Habilitar a página de login padrão do Spring
                        formLogin
                                .loginPage("/login") // Define uma página de login personalizada
                                .permitAll()
                )
                .logout(logout -> logout.permitAll()) // Permitir logout
                .httpBasic(Customizer.withDefaults()); // Habilitar HTTP Basic Authentication (maneira atualizada)

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
