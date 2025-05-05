package com.reto.cliente.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.reto.cliente.model.Cliente;

class ClienteTest {
    @Test
    void testClienteCreation() {
        Cliente cliente = new Cliente();
        cliente.setContrasena("secure123");
        cliente.setEstado(true);
        
        assertNotNull(cliente);
        assertEquals("secure123", cliente.getContrasena());
        assertTrue(cliente.getEstado());
    }
}