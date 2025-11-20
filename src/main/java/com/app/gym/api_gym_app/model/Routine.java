package com.app.gym.api_gym_app.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter // Reemplaza a @Data
@Setter // Reemplaza a @Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "routines")
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Fundamental para JPA
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // El hash solo se calcula con el ID
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @ToString.Exclude // Evita ciclo infinito al imprimir logs
    private User student;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL)
    @ToString.Exclude // Evita cargar la lista lazy al imprimir logs
    private Set<Exercise> exercises;
}