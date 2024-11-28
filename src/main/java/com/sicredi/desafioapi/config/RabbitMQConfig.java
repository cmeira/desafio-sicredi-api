package com.sicredi.desafioapi.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Configuração da fila de resultados
    @Bean
    public Queue resultadoQueue() {
        return new Queue("resultadoQueue", false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding binding(Queue resultadoQueue, TopicExchange exchange) {
        return BindingBuilder.bind(resultadoQueue).to(exchange).with("resultado.#");
    }

    // Configuração da fila de sessões com Dead Letter
    @Bean
    public Queue sessaoQueue() {
        return QueueBuilder.durable("sessaoQueue")
                .withArgument("x-dead-letter-exchange", "sessaoDLExchange") // Redireciona mensagens expiradas
                .build();
    }

    @Bean
    public Queue sessaoDLQueue() {
        return QueueBuilder.durable("sessaoDLQueue").build();
    }

    @Bean
    public DirectExchange sessaoDLExchange() {
        return new DirectExchange("sessaoDLExchange");
    }

    @Bean
    public Binding sessaoDLBinding(Queue sessaoDLQueue, DirectExchange sessaoDLExchange) {
        return BindingBuilder.bind(sessaoDLQueue).to(sessaoDLExchange).with("sessaoQueue");
    }
}

