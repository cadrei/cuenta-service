package com.reto.cliente.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clientes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cliente extends Persona {
    private String contrasena;
    private Boolean estado = true;
	
    public Cliente() {
    	super();
    }
    
    public Cliente(Long id, String nombre, String genero, Integer edad, String identificacion, String direccion,
			String telefono, String contrasena, Boolean estado) {
		super(id, nombre, genero, edad, identificacion, direccion, telefono);
		this.contrasena = contrasena;
		this.estado = estado;
	}
    
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
    
    
}