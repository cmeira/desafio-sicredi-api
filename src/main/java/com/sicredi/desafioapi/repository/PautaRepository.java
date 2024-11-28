package com.sicredi.desafioapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicredi.desafioapi.model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
