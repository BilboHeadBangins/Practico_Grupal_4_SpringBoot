package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.service;

import com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.dto.FacturaReporteDTO;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author italo
 */
@Service
public class FacturaService {
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

        TypedQuery<FacturaReporteDTO> query = em.createQuery(jpql.toString(),
        FacturaReporteDTO.class);
        params.forEach(query::setParameter);
        return query.getResultList();
    }
    
    
    //Parte 2. Todo esto debería ir en otro lado, para separar mejor las responsabilidades.
    public static final String FILE = "ReporteFacturas.pdf";
    
    public static final String FILETXT = "ReporteFacturas.txt";

    public static String jpql = "SELECT new com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.dto.FacturaReporteDTO(" +
    " f.numero, " +
    " f.fechaEmision, " +
    " COALESCE(c.denominacion, 'Consumidor Final'), " +
    " ci.denominacion, " +
    " pv.descripcion, " +
    " f.importeTotal, " +
    " COUNT(d) " +
    ") " +
    "FROM FacturaVenta f " +
    "LEFT JOIN f.cliente c " +
    "JOIN f.condicionIva ci " +
    "JOIN f.puntoVenta pv " +
    "JOIN f.detalles d " +
    "GROUP BY f.id, f.numero, f.fechaEmision, c.denominacion, ci.denominacion, pv.descripcion, f.importeTotal";
    
    
    

    public void createPdf(String dest) throws FileNotFoundException, IOException {
        
       ArrayList<FacturaReporteDTO> listaFacturaDTO = (ArrayList<FacturaReporteDTO>) em.createQuery(jpql, FacturaReporteDTO.class).getResultList(); 
        
        try (PdfWriter writer = new PdfWriter(dest); 
                
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            // Add metadata
            pdf.getDocumentInfo().setTitle("Reporte facturas de venta, sistema de gestión");

            // Start a new page
            //document.add(new AreaBreak());

            // Add content
            
            createTable(document,listaFacturaDTO);
        }
    }


    public static void createTable(Document document, ArrayList<FacturaReporteDTO> resultList) {
        // Create table with 7 columns
        Table table = new Table(UnitValue.createPercentArray(new float[]{1.2f,1.2f,1.2f,1.2f,1.2f,1.2f,1.2f}))
                .useAllAvailableWidth();


        table.addHeaderCell(new Cell().add(new Paragraph("Número de Factura")
                .setTextAlignment(TextAlignment.LEFT)
                .setBold()));
        
        table.addHeaderCell(new Cell().add(new Paragraph("Fecha de emisión")
                .setTextAlignment(TextAlignment.LEFT)
                .setBold()));
        
        table.addHeaderCell(new Cell().add(new Paragraph("Denominación cliente")
                .setTextAlignment(TextAlignment.LEFT)
                .setBold()));
        
        table.addHeaderCell(new Cell().add(new Paragraph("Condición iva")
                .setTextAlignment(TextAlignment.LEFT)
                .setBold()));

        table.addHeaderCell(new Cell().add(new Paragraph("Descripción del punto de venta")
                .setTextAlignment(TextAlignment.LEFT)
                .setBold()));
        
        table.addHeaderCell(new Cell().add(new Paragraph("Importe total")
                .setTextAlignment(TextAlignment.LEFT)
                .setBold()));
        
        table.addHeaderCell(new Cell().add(new Paragraph("Cantidad de items")
                .setTextAlignment(TextAlignment.LEFT)
                .setBold()));
        
        // Add data cells
        for (FacturaReporteDTO f : resultList) {
            
            table.addCell(String.valueOf(f.getNumeroFactura()));
            table.addCell(String.valueOf(f.getFechaEmision()));
            table.addCell(String.valueOf(f.getClienteDenominacion())); 
            table.addCell(String.valueOf(f.getCondicionIva()));
            table.addCell(String.valueOf(f.getPuntoVentaDescripcion()));
            table.addCell(String.valueOf(f.getImporteTotal()));
            table.addCell(String.valueOf(f.getCantidadItems()));
        }
                
        document.add(table);
    }
    
    public void crearTsv(String destino) throws IOException {
        ArrayList<FacturaReporteDTO> listaFacturaDTO = (ArrayList<FacturaReporteDTO>) em.createQuery(jpql, FacturaReporteDTO.class).getResultList();
        
        try (FileWriter writer = new FileWriter(destino, StandardCharsets.UTF_8)) {
        writer.write("Número\tFecha\tCliente\tCondición IVA\tPunto de venta\tImporte\tItems\n");
        for (FacturaReporteDTO f : listaFacturaDTO) {
            writer.write(f.getNumeroFactura() + "\t"
                       + f.getFechaEmision() + "\t"
                       + f.getClienteDenominacion()+"\t"
                       + f.getCondicionIva()+"\t"
                       + f.getPuntoVentaDescripcion()+"\t"
                       + f.getImporteTotal()+"\t"
                       + f.getCantidadItems() + "\n");
        }
    }
}
}
