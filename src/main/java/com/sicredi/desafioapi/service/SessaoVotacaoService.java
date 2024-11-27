package com.sicredi.desafioapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicredi.desafioapi.repository.SessaoVotacaoRepository;
import com.sicredi.desafioapi.domain.SessaoVotacao;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessaoVotacaoService {

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

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
