//package com.example.springbootwebsocket.config;
//
//import com.example.springbootwebsocket.utils.RestConstants;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class Config {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .cors()
//                .and()
//                .csrf()
//                .disable()
//                .authorizeHttpRequests(
//                        auth ->
//                                auth
//                                        .antMatchers(RestConstants.OPEN_PAGES)
//                                        .permitAll()
//                                        .antMatchers(HttpMethod.OPTIONS)
//                                        .permitAll()
//                                        .antMatchers("/",
//                                                "/favicon.ico",
//                                                "//*.png",
//                                                "//*.gif",
//                                                "//*.svg",
//                                                "//*.jpg",
//                                                "//*.html",
//                                                "//*.css",
//                                                "//*.js",
//                                                "/swagger-ui.html",
//                                                "/swagger-resources/",
//                                                "/v2/",
//                                                "/csrf",
//                                                "/webjars/",
//                                                "/v2/api-docs",
//                                                "/configuration/ui")
//                                        .permitAll()
//                                        .antMatchers("/api/**")
//                                        .authenticated())
//                .exceptionHandling();
////                .rememberMe()
////                .and()
//        return http.build();
//    }
//
//}
