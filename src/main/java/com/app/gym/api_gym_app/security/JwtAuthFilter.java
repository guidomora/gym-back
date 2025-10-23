package com.app.gym.api_gym_app.security;

import com.app.gym.api_gym_app.service.JwtService; // Necesitarás crear este servicio después
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    // Se inyectarán el servicio JWT y el servicio de detalles de usuario
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extraer el token después de "Bearer "
        jwt = authHeader.substring(7);
        
        // Asumimos que JwtService tiene el método extractUsername (a crear)
        userEmail = jwtService.extractUsername(jwt); 

        // Si se extrajo el email y el usuario no está autenticado
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Cargar el usuario de la base de datos
            var userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            
            // Asumimos que JwtService tiene el método isTokenValid (a crear)
            if (jwtService.isTokenValid(jwt, userDetails)) {
                
                // Crear token de autenticación para Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // Establecer la autenticación en el SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}