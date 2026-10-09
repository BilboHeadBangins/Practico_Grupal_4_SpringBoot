package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
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
@Table (name="lista_precio_articulo")
public class ListaPrecioArticulo extends AuditoriaApp{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lista_precio_id", nullable = false)
    private ListaPrecio listaPrecio;
    @Column(name="precio_venta", nullable = false)
    private double precioVenta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="articulo_id", nullable = false)
    private Articulo articulo;
}
