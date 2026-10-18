package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author italo
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FacturaReporteDTO {
    private Long numeroFactura;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaEmision;
    private String clienteDenominacion;
    private String condicionIva;
    private String puntoVentaDescripcion;
    private Double importeTotal;
    private Long cantidadItems;


}
