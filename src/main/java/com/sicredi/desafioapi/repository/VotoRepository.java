package com.sicredi.desafioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

import com.sicredi.desafioapi.domain.SessaoVotacao;
import com.sicredi.desafioapi.domain.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    List<Voto> findBySessaoVotacao(SessaoVotacao sessaoVotacao);
}
