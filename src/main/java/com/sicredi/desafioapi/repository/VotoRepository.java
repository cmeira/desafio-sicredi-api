package com.sicredi.desafioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicredi.desafioapi.model.SessaoVotacao;
import com.sicredi.desafioapi.model.Voto;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    List<Voto> findBySessaoVotacao(SessaoVotacao sessaoVotacao);
    long countBySessaoVotacaoAndVoto(SessaoVotacao sessaoVotacao, String voto);

}
