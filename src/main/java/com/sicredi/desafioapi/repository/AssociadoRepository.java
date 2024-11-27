package com.sicredi.desafioapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sicredi.desafioapi.domain.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    @Query("SELECT a FROM Associado a WHERE a.cpf = :cpf")
    Associado findByCpf(@Param("cpf") String cpf);

    @Query("SELECT a FROM Associado a WHERE a.email = :email")
    Associado findByEmail(@Param("email") String email);
}