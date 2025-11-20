package com.app.gym.api_gym_app.model;

import com.app.gym.api_gym_app.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder; 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder 
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") 
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; 

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Routine> routines;

    //Manipulado solo en entrenadores
    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    
    public User(String name, String email, String password, String phoneNumber, LocalDate birthdate, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.role = role;
        this.routines = new HashSet<>();
        this.gym = null;
    }
    
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

    @Override
    public String getUsername() {
        return this.email;
    }
}