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
@Table (name="punto_venta")
public class PuntoVenta extends AuditoriaApp{
    @Column(nullable = false)
    private int numero;
    private String descripcion;
    @Column(name="tipoemision")
    private String tipoEmision;
    @Column(name="domiciliocomercial")
    private String domicilioComercial;
}
