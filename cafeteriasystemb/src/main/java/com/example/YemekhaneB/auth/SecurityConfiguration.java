package com.example.YemekhaneB.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration {
    private final jwtTokenFilter jwtTokenFilter;
    private final AuthenticationProvider AuthenticationProvider;
    SecurityConfiguration(jwtTokenFilter jwtTokenFilter,AuthenticationProvider AuthenticationProvider){
        this.jwtTokenFilter = jwtTokenFilter;
        this.AuthenticationProvider = AuthenticationProvider;
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:4200");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addExposedHeader("Authorization");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
       http
               .cors(cors -> cors.configurationSource(corsConfigurationSource()))
               .csrf(csrf -> csrf.disable()).authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/api/auth/create", "/api/auth/login",
                                "/swagger-ui/**","/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/users","api/","/api/adminu",
                                "/api/{id}","api/transactions/admin/**","api/adminyemekkonsolu/admin/**",
                                "api/titles","api/roles","api/workplace").hasRole("Admin") // Specify admin endpoints here
                        .anyRequest().authenticated()
        ).sessionManagement(sessionManagement ->
                       sessionManagement
                               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               ).authenticationProvider(AuthenticationProvider)
               .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
