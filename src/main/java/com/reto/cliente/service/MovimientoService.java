package com.reto.cliente.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reto.cliente.exception.CuentaNotFoundException;
import com.reto.cliente.exception.SaldoNoDisponibleException;
import com.reto.cliente.model.Cuenta;
import com.reto.cliente.model.Movimiento;
import com.reto.cliente.repository.CuentaRepository;
import com.reto.cliente.repository.MovimientoRepository;

@Service
public class MovimientoService {
    @Autowired
    private CuentaRepository cuentaRepo;
    @Autowired
    private MovimientoRepository movimientoRepo;

    @Transactional
    public Movimiento crearMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepo.findById(movimiento.getNumeroCuenta()).orElseThrow(() -> new CuentaNotFoundException());
        
        BigDecimal nuevoSaldo = cuenta.getSaldoActual().add(movimiento.getValor());
        if(nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoNoDisponibleException();
        }
        
        cuenta.setSaldoActual(nuevoSaldo);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setFecha(LocalDate.now());
        
        cuentaRepo.save(cuenta);
        return movimientoRepo.save(movimiento);
    }
}
