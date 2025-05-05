package com.reto.cliente.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ClienteInfo {
    @Id
    private Long id;
    private String nombre;
    private Boolean estado;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
    
    
}