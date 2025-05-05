package com.reto.cliente;

import org.springframework.boot.SpringApplication;

public class TestClienteApplication {

	public static void main(String[] args) {
		SpringApplication.from(ClienteApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
