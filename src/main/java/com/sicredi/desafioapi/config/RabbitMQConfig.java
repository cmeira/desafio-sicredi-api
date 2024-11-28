package com.sicredi.desafioapi.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

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
}
