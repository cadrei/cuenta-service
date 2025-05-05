package com.reto.cliente.controller;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reto.cliente.repository.CuentaRepository;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    @Autowired
    private CuentaRepository cuentaRepo;

    @GetMapping
    public List<Map<String, Object>> generarReporte(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate fechaFin) {
        
        return cuentaRepo.findByClienteIdWithMovimientos(clienteId).stream()
            .map(cuenta -> {
                Map<String, Object> reporte = new LinkedHashMap<>();
                reporte.put("numeroCuenta", cuenta.getNumeroCuenta());
                reporte.put("saldoActual", cuenta.getSaldoActual());
                
                reporte.put("movimientos", cuenta.getMovimientos().stream()
                    .filter(m -> !m.getFecha().isBefore(fechaInicio) && !m.getFecha().isAfter(fechaFin))
                    .map(m -> Map.of(
                        "fecha", m.getFecha(),
                        "tipo", m.getTipoMovimiento(),
                        "valor", m.getValor(),
                        "saldo", m.getSaldo()
                    ))
                    .collect(Collectors.toList()));
                
                return reporte;
            }).collect(Collectors.toList());
    }
    
    /*@GetMapping
    public List<Map<String, Object>> generarReporte2(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate fechaFin) {
        
        return cuentaRepo.findByClienteId(clienteId).stream()
            .map(cuenta -> {
                Map<String, Object> reporte = new HashMap<>();
                reporte.put("numeroCuenta", cuenta.getNumeroCuenta());
                reporte.put("saldoActual", cuenta.getSaldoActual());
                reporte.put("movimientos", cuenta.getMovimientos().stream()
                    .filter(m -> m.getFecha().isAfter(fechaInicio) && m.getFecha().isBefore(fechaFin))
                    .collect(Collectors.toList()));
                return reporte;
            }).collect(Collectors.toList());
    }*/
}