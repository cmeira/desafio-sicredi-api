package com.sicredi.desafioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicredi.desafioapi.model.SessaoVotacao;

import java.time.LocalDateTime;
import java.util.List;

public interface SessaoVotacaoRepository extends JpaRepository<com.sicredi.desafioapi.model.SessaoVotacao, Long> {
    List<SessaoVotacao> findByDataHoraFimBefore(LocalDateTime dataHoraFim);
}