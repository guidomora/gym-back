package com.app.gym.api_gym_app.model;

import com.app.gym.api_gym_app.enums.UserRole;
import jakarta.persistence.*;
import lombok.*; // Importamos Getter, Setter, etc.
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 1. Quitamos @Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
// 2. Configuramos Equals y HashCode para usar SOLO el ID
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // 3. Marcamos el ID como único campo para el hash
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

    // 4. EXCLUIMOS las relaciones del toString para evitar ciclos al imprimir logs
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Routine> routines;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    @ToString.Exclude
    private Gym gym;

    @OneToOne
    @JoinColumn(name = "membership_id") // Recuerda que cambiamos esto en el SQL
    @ToString.Exclude
    private Membership membership;

    // ... resto de métodos de UserDetails (sin cambios)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    // ... constructores manuales y overrides ...
    public User(String name, String email, String password, String phoneNumber, LocalDate birthdate, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.role = role;
        this.routines = new HashSet<>();
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