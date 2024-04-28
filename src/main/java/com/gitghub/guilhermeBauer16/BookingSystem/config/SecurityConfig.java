package com.gitghub.guilhermeBauer16.BookingSystem.config;

import com.gitghub.guilhermeBauer16.BookingSystem.security.jwt.JwtTokenFilter;
import com.gitghub.guilhermeBauer16.BookingSystem.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Bean
    AuthenticationManager authenticationManagerBean(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        JwtTokenFilter customFilter = new JwtTokenFilter(tokenProvider);

        return http
                .httpBasic(basic -> basic.disable())
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests

                                .requestMatchers("/user/**").permitAll()
                                .requestMatchers("api/userPatterns/register/**").permitAll()
                                .requestMatchers("/permission/**").hasAuthority("ADMIN")
                                .requestMatchers("/permission/**").permitAll()
                                .requestMatchers("/user/**").permitAll()
                                .requestMatchers("/auth/login").permitAll()
                                .requestMatchers("/auth/signIn").permitAll()
                                .requestMatchers("/api/address/register/**").permitAll()
                                .requestMatchers("/api/**").authenticated()

                                .requestMatchers("/users").denyAll()
                )
                .cors(cors -> {})
                .build();

    }


}
