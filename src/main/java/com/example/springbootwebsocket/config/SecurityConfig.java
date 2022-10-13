package com.example.springbootwebsocket.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(
                        auth ->
                                auth

                                        .antMatchers("/**")
                                        .permitAll()
                                        .antMatchers("/",
                                                "/favicon.ico",
                                                "//*.png",
                                                "//*.gif",
                                                "//*.svg",
                                                "//*.jpg",
                                                "//*.html",
                                                "//*.css",
                                                "//*.js",
                                                "/swagger-ui.html",
                                                "/swagger-resources/",
                                                "/v2/",
                                                "/csrf",
                                                "/webjars/",
                                                "/v2/api-docs",
                                                "/configuration/ui")
                                        .permitAll()
                                        .antMatchers("/api/**")
                                        .authenticated())
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
