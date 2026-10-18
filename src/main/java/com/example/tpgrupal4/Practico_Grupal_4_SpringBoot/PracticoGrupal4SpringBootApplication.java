package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PracticoGrupal4SpringBootApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PracticoGrupal4SpringBootApplication.class, args);
                
                FacturaRestController reporte = context.getBean(FacturaRestController.class);
                try {
                    reporte.createPdf(reporte.FILE);
                    System.out.println("PDF creado exitosamente: " + reporte.FILE);
                    
                    reporte.crearTsv(reporte.FILETXT);
                    
                    System.out.println("TXT creado exitosamente: " + reporte.FILETXT);
                } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
