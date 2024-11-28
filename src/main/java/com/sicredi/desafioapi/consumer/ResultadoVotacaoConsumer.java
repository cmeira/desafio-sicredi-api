package com.sicredi.desafioapi.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.sicredi.desafioapi.dto.ResultadoVotacaoDTO;

@Component
public class ResultadoVotacaoConsumer{

    @RabbitListener(queues = "resultadoQueue")
    public void receberResultado(ResultadoVotacaoDTO resultado) {
        System.out.println("Resultado da votação: " + resultado.getSimCount() + " SIM, " + resultado.getNaoCount() + " NÃO");
    }
}

