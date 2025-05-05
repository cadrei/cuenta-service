package com.reto.cliente.repository;

import com.reto.cliente.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    List<Movimiento> findByCuentaNumeroCuenta(String numeroCuenta);

    @Query("SELECT m FROM Movimiento m WHERE m.cuenta.numeroCuenta = :numeroCuenta AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movimiento> findByCuentaAndFechaBetween(
            @Param("numeroCuenta") String numeroCuenta,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);

    @Query(value = "SELECT COUNT(*) FROM movimiento " +
            "WHERE numeroCuenta = :numeroCuenta " +
            "AND tipo_movimiento = 'RETIRO' " +
            "AND fecha = :fecha", 
            nativeQuery = true)
    int countRetirosDiarios(
            @Param("numeroCuenta") String numeroCuenta,
            @Param("fecha") LocalDate fecha);
}