package com.reto.cliente.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cuenta {
    @Id
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private BigDecimal saldoActual;
    private Boolean estado = true;
    private Long clienteId;
    
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movimiento> movimientos = new ArrayList<>();

    // Getter y Setter para movimientos
    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
    
	public Cuenta(String numeroCuenta, String tipo, BigDecimal saldoInicial, BigDecimal saldoActual, Boolean estado,Long clienteId) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.tipo = tipo;
		this.saldoInicial = saldoInicial;
		this.saldoActual = saldoActual;
		this.estado = estado;
		this.clienteId = clienteId;
	}
	
	public Cuenta(String numeroCuenta, String tipo, BigDecimal saldoInicial, Boolean estado,Long clienteId) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.tipo = tipo;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
		this.clienteId = clienteId;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	public BigDecimal getSaldoActual() {
		return saldoActual;
	}
	public void setSaldoActual(BigDecimal saldoActual) {
		this.saldoActual = saldoActual;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
    
    
}