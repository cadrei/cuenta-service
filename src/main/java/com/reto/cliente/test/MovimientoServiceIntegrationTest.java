package com.reto.cliente.test;

import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.reto.cliente.exception.SaldoNoDisponibleException;
import com.reto.cliente.model.Cuenta;
import com.reto.cliente.model.Movimiento;
import com.reto.cliente.repository.CuentaRepository;
import com.reto.cliente.service.MovimientoService;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MovimientoServiceIntegrationTest {
    @Autowired
    private MovimientoService service;
    
    @Autowired
    private CuentaRepository cuentaRepo;

    @Test
    void testMovimientoSinSaldo() {
        Cuenta cuenta = new Cuenta("12345", "Ahorro", BigDecimal.ZERO, BigDecimal.ZERO, true, 1L);
        cuentaRepo.save(cuenta);
        
        Movimiento movimiento = new Movimiento();
        movimiento.setValor(new BigDecimal("-100"));
        movimiento.setNumeroCuenta("12345");
        
        assertThrows(SaldoNoDisponibleException.class, () -> {
            service.crearMovimiento(movimiento);
        });
    }
}
