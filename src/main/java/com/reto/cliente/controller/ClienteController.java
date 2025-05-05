package com.reto.cliente.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.reto.cliente.exception.ClienteNotFoundException;
import com.reto.cliente.model.Cliente;
import com.reto.cliente.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController implements Serializable{

	private static final long serialVersionUID = 1L;
	@Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) {
        Cliente saved = clienteRepository.save(cliente);
        rabbitTemplate.convertAndSend("cliente-events", "cliente.created", saved);
        return saved;
    }

    //GET Todos los clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes;
			clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }
    
    // GET Cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
        return ResponseEntity.ok(cliente);
    }
    
 // PUT Actualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(
            @PathVariable Long id, 
            @RequestBody Cliente clienteActualizado) {
        
        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));

        // Actualizar campos permitidos
        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setGenero(clienteActualizado.getGenero());
        clienteExistente.setEdad(clienteActualizado.getEdad());
        clienteExistente.setDireccion(clienteActualizado.getDireccion());
        clienteExistente.setTelefono(clienteActualizado.getTelefono());
        clienteExistente.setEstado(clienteActualizado.getEstado());
        clienteExistente.setContrasena(clienteActualizado.getContrasena());

        Cliente clienteActualizadoDB = clienteRepository.save(clienteExistente);
        
        // Enviar evento de actualización
        rabbitTemplate.convertAndSend("cliente-events", "cliente.updated", clienteActualizadoDB);
        
        return ResponseEntity.ok(clienteActualizadoDB);
    }
    
 // DELETE Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ClienteNotFoundException(id));
        
        clienteRepository.delete(cliente);
        
        // Enviar evento de eliminación
        rabbitTemplate.convertAndSend("cliente-events", "cliente.deleted", id);
        
        return ResponseEntity.noContent().build();
    }
    
}