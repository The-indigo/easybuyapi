package com.example.easybuyapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {


    /**
     * Indicates that the AuthenticationProvider should be autowired
     * by spring's dependency injection mechanism.
     **/
    @Autowired
    private AuthenticationProvider authenticationProvider;


    /**
     * Indicates that the JwtAuthenticationFilter should be autowired
     * by spring's dependency injection mechanism.
     **/
    @Autowired
private JwtAuthenticationFilter jwtFilter;



/**
     * Spring's Securityfilterchain implementation.
     * @return SecurityFilterChain.
     * @param http
     **/
@Bean
public SecurityFilterChain securityFilterChain(
    final HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .authorizeHttpRequests()
            .requestMatchers("/easybuyapi/v1/register",
            "/easybuyapi/v1/login")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(
                jwtFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
    }
}
