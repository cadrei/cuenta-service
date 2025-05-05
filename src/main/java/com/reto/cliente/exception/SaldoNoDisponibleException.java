package com.reto.cliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SaldoNoDisponibleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SaldoNoDisponibleException() {
        super("Saldo no disponible para realizar la operación");
    }

    public SaldoNoDisponibleException(String mensajePersonalizado) {
        super(mensajePersonalizado);
    }
}