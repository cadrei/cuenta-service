package com.reto.cliente.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.reto.cliente.model.Cuenta;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CuentaControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCRUDCuenta() {
        // Create
        Cuenta cuenta = new Cuenta("123456", "Ahorro", new BigDecimal("1000"), true, 1L);
        ResponseEntity<Cuenta> response = restTemplate.postForEntity("/cuentas", cuenta, Cuenta.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Read
        Cuenta cuentaCreada = restTemplate.getForObject("/cuentas/" + cuenta.getNumeroCuenta(), Cuenta.class);
        assertEquals("Ahorro", cuentaCreada.getTipo());

        // Update
        cuenta.setTipo("Corriente");
        restTemplate.put("/cuentas/" + cuenta.getNumeroCuenta(), cuenta);
        Cuenta cuentaActualizada = restTemplate.getForObject("/cuentas/" + cuenta.getNumeroCuenta(), Cuenta.class);
        assertEquals("Corriente", cuentaActualizada.getTipo());

        // Delete
        restTemplate.delete("/cuentas/" + cuenta.getNumeroCuenta());
        ResponseEntity<Cuenta> responseDelete = restTemplate.getForEntity("/cuentas/" + cuenta.getNumeroCuenta(), Cuenta.class);
        assertEquals(HttpStatus.NOT_FOUND, responseDelete.getStatusCode());
    }
}