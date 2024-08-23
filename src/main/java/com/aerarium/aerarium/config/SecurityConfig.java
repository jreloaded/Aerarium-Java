package com.aerarium.aerarium.config;


import com.aerarium.aerarium.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //Esta anotación indica que la clase es de configuración
@EnableWebSecurity// Esta anotación se utiliza para habilitar la seguridad web en una aplicación Spring Boot.
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;


    @Bean //Gracias a esto nos permite crear el objeto
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                //Cadenas de filtros
                //Csrf proteccion Cross Site Request Forgery(CSRF), es una medida para las solicitudes POST tengan un token csrf valido
                .csrf(csrf -> csrf.disable())//Desahibilitar segurida csrf colocado por springboot automaticamente
                .authorizeHttpRequests(authRequest ->
                authRequest
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }

}
