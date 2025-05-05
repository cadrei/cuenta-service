package com.reto.cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.cliente.model.Cuenta;
import com.reto.cliente.repository.CuentaRepository;

@Service
public class ReporteService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> generarReporteCuentasActivas(Long clienteId) {
        return cuentaRepository.findActiveAccountsByClient(clienteId);
    }
    
    /*public List<CuentaDto> getResumenCuentas(Long clienteId) {
        return cuentaRepository.findAccountsSummary(clienteId);
    }*/
}