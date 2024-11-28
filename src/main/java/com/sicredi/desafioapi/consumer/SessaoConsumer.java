package com.sicredi.desafioapi.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sicredi.desafioapi.model.SessaoVotacao;
import com.sicredi.desafioapi.service.SessaoVotacaoService;

@Component
public class SessaoConsumer {

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    @RabbitListener(queues = "sessaoDLQueue")
    public void handleSessaoFechamento(SessaoVotacao sessaoVotacao) {
        sessaoVotacaoService.encerrarSessaoVotacao(sessaoVotacao);
    }
}
