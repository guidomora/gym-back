package com.app.gym.api_gym_app.model;

import com.app.gym.api_gym_app.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // Contiene el hash BCRYPT

    private String phoneNumber;
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    // Métodos de la interfaz UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convierte el rol (TRAINER/STUDENT) a un permiso (Authority)
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
    
    // Dejamos las demás funciones de UserDetails por defecto
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}