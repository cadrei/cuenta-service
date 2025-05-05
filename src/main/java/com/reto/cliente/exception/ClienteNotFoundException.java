package com.reto.cliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException(Long id) {
        super("Cliente no encontrado con ID: " + id);
    }

    public ClienteNotFoundException(String identificacion) {
        super("Cliente no encontrado con identificación: " + identificacion);
    }
}