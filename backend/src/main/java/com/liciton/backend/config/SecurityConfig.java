package com.liciton.backend.config;

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
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desabilita proteção CSRF (necessário para APIs REST)
            .authorizeHttpRequests(auth -> auth
                // Permite que qualquer um (sem login) acesse a criação de usuários (POST)
                .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
                // Futuramente liberaremos o /login aqui também
                // Qualquer outra rota exige estar logado
                .anyRequest().authenticated()
            );

        return http.build();
    }

    // Cria o "Hash" da senha (para não salvarmos a senha pura no banco)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}