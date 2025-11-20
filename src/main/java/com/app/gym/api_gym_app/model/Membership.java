package com.app.gym.api_gym_app.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "memberships")
public class Membership {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Generar key con generador de string (32 caracteres). Convertir string a QR
    @Column(nullable = false)
    private String membershipKey;

    //Se actualiza (dia actual + 30) al asignar una membresia a un usuario
    @Column
    @Builder.Default
    private LocalDate expirationDate = null;

    public boolean isValid() {
        if (expirationDate != null) {
            if (expirationDate.isAfter(LocalDate.now()) ) {
                return true;
            }
        }
        return false;
    }
}
