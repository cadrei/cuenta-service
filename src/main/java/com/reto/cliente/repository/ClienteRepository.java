package com.reto.cliente.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.reto.cliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
}