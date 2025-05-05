package com.reto.cliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CuentaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 708533775662533184L;

	public CuentaNotFoundException(String numeroCuenta) {
        super("Cuenta no encontrada con número: " + numeroCuenta);
    }
    
    public CuentaNotFoundException(Long clienteId) {
        super("No se encontraron cuentas para el cliente con ID: " + clienteId);
    }

	public CuentaNotFoundException() {
		super();
	}
    
}