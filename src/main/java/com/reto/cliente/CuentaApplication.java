package com.reto.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.reto.cliente", "com.reto.cliente.commons"})
public class CuentaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CuentaApplication.class, args);
        System.out.println("\n=== Microservicio de Cuentas iniciado correctamente ===");
        System.out.println("Endpoints disponibles:");
        System.out.println("POST   /cuentas");
        System.out.println("GET    /cuentas");
        System.out.println("GET    /cuentas/{numeroCuenta}");
        System.out.println("PUT    /cuentas/{numeroCuenta}");
        System.out.println("DELETE /cuentas/{numeroCuenta}");
        System.out.println("POST   /movimientos");
        System.out.println("GET    /reportes?clienteId=X&fechaInicio=YYYY-MM-DD&fechaFin=YYYY-MM-DD\n");
    }
}