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
@Table(name = "tipo_moneda")
public class TipoMoneda extends AuditoriaApp{
    @Column(name="codigoafip",nullable = false)
    private String codigoAfip;
    @Column(nullable = false)
    private String denominacion;
    @Column(nullable = false)
    private String simbolo;
}
