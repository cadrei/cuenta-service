package com.reto.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.reto.cliente", "com.reto.cliente.commons"})
public class ClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteApplication.class, args);
		System.out.println("\n=== Microservicio de Clientes iniciado correctamente ===");
        System.out.println("Endpoints disponibles:");
        System.out.println("POST   /clientes");
        System.out.println("GET    /clientes");
        System.out.println("GET    /clientes/{id}");
        System.out.println("PUT    /clientes/{id}");
        System.out.println("DELETE /clientes/{id}\n");
	}

}
