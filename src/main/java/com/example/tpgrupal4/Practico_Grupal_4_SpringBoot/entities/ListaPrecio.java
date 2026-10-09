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
@Table (name="lista_precio")
public class ListaPrecio extends AuditoriaApp {
    @Column(nullable = false)
    private String codigo;
    @Column(nullable = false)
    private String denominacion;
}
