package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot;

import com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.dto.FacturaReporteDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author italo
 */
@RestController
@RequestMapping("/api/facturas")
public class FacturaRestController {
    @PersistenceContext
    private EntityManager em;
    
    @GetMapping
    public List<FacturaReporteDTO> buscarFacturasFiltradas(LocalDate fechaDesde, LocalDate fechaHasta, String estado, Double montoMinimo){

        StringBuilder jpql = new StringBuilder(
        "SELECT new com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.dto.FacturaReporteDTO(" +
        " f.numero, f.fechaEmision, COALESCE(c.denominacion, 'Consumidor Final'), " +
        " ci.denominacion, pv.descripcion, f.importeTotal, COUNT(d)" +") " +
        "FROM FacturaVenta f " +
        "LEFT JOIN f.cliente c " +
        "JOIN f.condicionIva ci " +
        "JOIN f.puntoVenta pv " +
        "JOIN f.detalles d " +
        "WHERE 1=1 "
        );
        Map<String, Object> params = new HashMap<>();
        if (fechaDesde != null) {
        jpql.append("AND f.fechaEmision >= :fechaDesde ");
        params.put("fechaDesde", fechaDesde);
        }
        if (fechaHasta != null) {
        jpql.append("AND f.fechaEmision <= :fechaHasta ");
        params.put("fechaHasta", fechaHasta);
        }
        if (estado != null && !estado.trim().isEmpty()) {
        jpql.append("AND f.estado = :estado ");
        params.put("estado", estado);
        }
        if (montoMinimo != null) {
        jpql.append("AND f.importeTotal >= :montoMinimo ");
        params.put("montoMinimo", montoMinimo);
        }
        jpql.append("GROUP BY f.id, f.numero, f.fechaEmision, c.denominacion,ci.denominacion, pv.descripcion, f.importeTotal");
        
        //cómo usar el entity manager en spring? o no? uso otra cosa?
        TypedQuery<FacturaReporteDTO> query = em.createQuery(jpql.toString(),
        FacturaReporteDTO.class);
        params.forEach(query::setParameter);
        return query.getResultList();
    }
}
