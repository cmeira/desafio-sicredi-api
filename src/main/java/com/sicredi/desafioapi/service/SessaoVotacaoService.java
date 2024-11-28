package com.sicredi.desafioapi.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicredi.desafioapi.dto.ResultadoVotacaoDTO;
import com.sicredi.desafioapi.model.Pauta;
import com.sicredi.desafioapi.model.SessaoVotacao;
import com.sicredi.desafioapi.repository.PautaRepository;
import com.sicredi.desafioapi.repository.SessaoVotacaoRepository;
import com.sicredi.desafioapi.repository.VotoRepository;

import jakarta.transaction.Transactional;

@Service
public class SessaoVotacaoService {

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;
    @Autowired
    private PautaRepository pautaRepository;
    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    // Método para abrir sessão de votação
    public SessaoVotacao abrirSessaoVotacao(Pauta pauta, LocalDateTime dataAbertura, Duration duracao) {
        SessaoVotacao sessao = new SessaoVotacao();
        sessao.setPauta(pauta);
        sessao.setDataAbertura(dataAbertura);
        sessao.setDataFechamento(dataAbertura.plus(duracao != null ? duracao : Duration.ofMinutes(1)));
        sessao = sessaoVotacaoRepository.save(sessao);

        // Agendar o fechamento da sessão
        agendarFechamentoSessaoVotacao(sessao);
        
        return sessao;
    }

    public void agendarFechamentoSessaoVotacao(SessaoVotacao sessao) {
        LocalDateTime dataFechamento = sessao.getDataFechamento();
        long delayMillis = Duration.between(LocalDateTime.now(), dataFechamento).toMillis();
    
        if (delayMillis > 0) {
            rabbitTemplate.convertAndSend("sessaoQueue", sessao, message -> {
                message.getMessageProperties().setExpiration(String.valueOf(delayMillis));
                return message;
            });
        }
    }

    // Encerrar a sessão de votação
    @Transactional
    public void encerrarSessaoVotacao(SessaoVotacao sessaoVotacao) { 

        // Marca a sessão como encerrada
        sessaoVotacao.setFechada(true);
        sessaoVotacaoRepository.save(sessaoVotacao);

        // Conta os votos
        long simCount = votoRepository.countBySessaoVotacaoAndVoto(sessaoVotacao, "SIM");
        long naoCount = votoRepository.countBySessaoVotacaoAndVoto(sessaoVotacao, "NAO");

        // Envia o resultado via RabbitMQ
        ResultadoVotacaoDTO resultado = new ResultadoVotacaoDTO(simCount, naoCount);
        rabbitTemplate.convertAndSend("resultadoQueue", resultado);
    }

    public SessaoVotacao obterSessaoVotacao(Long sessaoVotacaoId) {
        return sessaoVotacaoRepository.findById(sessaoVotacaoId)
                .orElseThrow(() -> new RuntimeException("Sessão de votação não encontrada para o ID: " + sessaoVotacaoId));
    }

    // Salvar ou atualizar uma Sessão de Votação
    public SessaoVotacao salvar(SessaoVotacao sessaoVotacao) {
        return sessaoVotacaoRepository.save(sessaoVotacao);
    }

    // Listar todas as Sessões de Votação
    public List<SessaoVotacao> listarTodas() {
        return sessaoVotacaoRepository.findAll();
    }

    // Buscar uma Sessão de Votação por ID
    public SessaoVotacao buscarPorId(Long id) {
        return sessaoVotacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sessão de Votação não encontrada para o ID: " + id));
    }

    // Deletar uma Sessão de Votação pelo ID
    public void deletarPorId(Long id) {
        SessaoVotacao sessao = buscarPorId(id);
        sessaoVotacaoRepository.delete(sessao);
    }

    public List<SessaoVotacao> listarSessoesAtivas() {
        return sessaoVotacaoRepository.findByDataHoraFimBefore(
                LocalDateTime.now());
    }

}
