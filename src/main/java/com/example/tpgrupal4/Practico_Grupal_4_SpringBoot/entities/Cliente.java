package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author italo
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table (name="cliente")
public class Cliente extends AuditoriaApp{
    @Column(name="cuitcuil",nullable = false)
    private String cuitCuil;
    @Column(nullable = false)
    private String denominacion;
    @OneToOne
    @JoinColumn(name = "contacto_id", nullable = false)
    private Contacto contacto;
    @OneToOne
    @JoinColumn(name="domicilio_id", nullable = false)
    private Domicilio domicilio;  
}
