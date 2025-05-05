package com.reto.cliente.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public TopicExchange clienteEventsExchange() {
        return new TopicExchange("cliente-events");
    }
}