package com.reto.cliente.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.cliente.exception.CuentaNotFoundException;
import com.reto.cliente.model.Cuenta;
import com.reto.cliente.repository.CuentaRepository;


@Service
public class CuentaService {
	
    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> obtenerCuentasActivasPorCliente(Long clienteId) {
        return cuentaRepository.findByClienteIdAndEstado(clienteId, true);
    }
    
    public Cuenta actualizarSaldo(String numeroCuenta, BigDecimal nuevoSaldo) {
        Cuenta cuenta = cuentaRepository.findById(numeroCuenta).orElseThrow(() -> new CuentaNotFoundException(numeroCuenta));
        cuenta.setSaldoActual(nuevoSaldo);
        return cuentaRepository.save(cuenta);
    }
}