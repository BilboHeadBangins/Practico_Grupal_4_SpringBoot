package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot;

import com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.dto.FacturaReporteDTO;
import com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.service.FacturaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author italo
 */
@RestController
@RequestMapping("/api/facturas")
public class FacturaRestController {
    private final FacturaService facturaService;

    public FacturaRestController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public List<FacturaReporteDTO> buscarFacturasFiltradas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Double montoMinimo) {
        return facturaService.buscarFacturasFiltradas(fechaDesde, fechaHasta, estado, montoMinimo);
    }   
}
