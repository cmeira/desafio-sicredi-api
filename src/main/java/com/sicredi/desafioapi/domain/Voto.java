package com.sicredi.desafioapi.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data  
@NoArgsConstructor
@AllArgsConstructor
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sessao_votacao_id")
    private SessaoVotacao sessaoVotacao;

    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;

    @Column(nullable = false)
    private String voto; // "Sim" ou "Não"

    // Getters e Setters
}
