// src/main/java/com/app/gym/api_gym_app/configuration/SecurityConfig.java
package com.app.gym.api_gym_app.configuration;

import com.app.gym.api_gym_app.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Permite usar @PreAuthorize en los métodos de los controladores
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitar CSRF ya que es una API REST stateless
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // 1. Endpoints públicos (Auth, Swagger)
                        .requestMatchers("/api/auth/**", 
                                         "/v3/api-docs/**",
                                         "/swagger-ui/**",
                                         "/swagger-ui.html"
                        ).permitAll()
                        
                        // 2. Proteger endpoints de Rutinas (ejemplo: solo el Entrenador puede crear)
                        // NOTA: Se recomienda usar @PreAuthorize en el Controller para la lógica fina
                        .requestMatchers(HttpMethod.POST, "/api/routines").hasAuthority("TRAINER")

                        // 3. Cualquier otra solicitud requiere autenticación
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess
                        // Configurar la API como stateless (sin sesiones)
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Configurar el proveedor de autenticación
                .authenticationProvider(authenticationProvider)
                // Agregar el filtro JWT ANTES del filtro de usuario/contraseña de Spring
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}