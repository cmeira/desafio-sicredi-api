package com.sicredi.desafioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicredi.desafioapi.domain.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
