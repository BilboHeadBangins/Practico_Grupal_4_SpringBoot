package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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
@Table (name="articulo")
public class Articulo extends AuditoriaApp {
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubro rubro;
    @Column(nullable = false)
    private String codigo;
    @Column(nullable = false)
    private String denominacion;
    @ManyToOne(fetch = FetchType.LAZY)
    private Marca marca;
}
