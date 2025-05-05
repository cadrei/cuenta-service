package com.reto.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import com.reto.cliente.model.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String> {
	// Consulta SQL nativa
    @Query(value = "SELECT * FROM cuenta WHERE saldo_actual >= :saldoMinimo AND tipo = :tipo", nativeQuery = true)
    List<Cuenta> findAccountsByBalanceAndType(@Param("saldoMinimo") BigDecimal saldoMinimo, @Param("tipo") String tipo);
    
	//Consulta nativa SQL
	@Query(value = "SELECT * FROM cuenta WHERE tipo = :tipo ORDER BY saldo_actual DESC", nativeQuery = true)
	List<Cuenta> encontrarCuentasPorTipoOrdenadas(@Param("tipo") String tipo);
    
    // Consulta JPQL personalizada
    @Query("SELECT c FROM Cuenta c WHERE c.clienteId = :clienteId AND c.estado = true")
    List<Cuenta> findActiveAccountsByClient(@Param("clienteId") Long clienteId);
    
    @Query("SELECT c FROM Cuenta c LEFT JOIN FETCH c.movimientos WHERE c.clienteId = :clienteId")
    List<Cuenta> findByClienteIdWithMovimientos(@Param("clienteId") Long clienteId);

    @Query("SELECT c FROM Cuenta c WHERE c.clienteId = :clienteId AND c.estado = :estado")
    List<Cuenta> findByClienteIdAndEstado(@Param("clienteId") Long clienteId, @Param("estado") Boolean estado);
    
    // Método personalizado para encontrar cuentas por clienteId
    List<Cuenta> findByClienteId(Long clienteId);
    
    // Método para buscar cuentas por estado
    List<Cuenta> findByEstado(Boolean estado);
    
    // Método para buscar cuentas por tipo y saldo mínimo
    List<Cuenta> findByTipoAndSaldoActualGreaterThanEqual(String tipo, BigDecimal saldo);
}
