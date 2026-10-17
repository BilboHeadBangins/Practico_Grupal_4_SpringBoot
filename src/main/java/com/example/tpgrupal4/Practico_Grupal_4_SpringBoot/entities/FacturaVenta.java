package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author italo
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true, exclude={"detalles"})
@ToString(exclude={"detalles"})

@Entity
@Table(name = "factura_venta")
public class FacturaVenta extends AuditoriaApp{
    private Long numero;
    @Column(name="fecha_emision",nullable = false)
    private LocalDate fechaEmision;
    
    //¿por qué no estaba antes?
    @ManyToOne
    @JoinColumn(name="cliente_id", nullable = true)
    private Cliente cliente;
    
    //lo mismo con esta. En la próxima ejecución tendré que cambiar los nullable de cliente y civa a false, o no, no sé.
    @ManyToOne
    @JoinColumn(name="condicion_iva_id", nullable = true)
    private CondicionIva condicionIva;
    
    @ManyToOne
    @JoinColumn(name="punto_venta_id", nullable=false)
    private PuntoVenta puntoVenta;

    @Column(name="importecobrado")
    private double importeCobrado;
    @Column(name="importesaldo")
    private double importeSaldo;

    @Column(name="importe_total", nullable = false)
    private double importeTotal;
    private String cae;
    @Column(name="caefechavencimiento")
    private LocalDate caeFechaVencimiento;
    @Column(name="resultadoafip")
    private String resultadoAfip;
    @Column(name="motivorechazo")
    private String motivoRechazo;

    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name="fechaanulacion")
    private LocalDate fechaAnulacion;
    private String observaciones;
    
    @OneToMany(mappedBy="factura", cascade=CascadeType.ALL) 
    private List<FacturaVentaDetalle> detalles = new ArrayList<>();
}
