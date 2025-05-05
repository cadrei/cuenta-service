package com.reto.cliente.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.cliente.model.Cliente;

@Service
public class ClienteService {

    private final RabbitTemplate rabbitTemplate;
    private final Jackson2JsonMessageConverter jsonConverter;

    @Autowired
    public ClienteService(RabbitTemplate rabbitTemplate,Jackson2JsonMessageConverter jsonConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.jsonConverter = jsonConverter;
        this.rabbitTemplate.setMessageConverter(jsonConverter);
    }

    public void publicarClienteCreado(Cliente cliente) {
        rabbitTemplate.convertAndSend(
            "exchange-name",
            "routing-key",
            cliente // Se enviará como JSON
        );
    }
}