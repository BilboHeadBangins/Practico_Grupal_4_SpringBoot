package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "usuario")
public class Usuario extends EntityId {
    @Column(nullable = false, unique=true)
    private String usuario;
    @Column(nullable = false)
    private String clave;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
}
