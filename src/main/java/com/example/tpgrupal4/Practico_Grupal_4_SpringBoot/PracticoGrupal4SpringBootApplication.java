package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }) 
public class PracticoGrupal4SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticoGrupal4SpringBootApplication.class, args);
	}

}
