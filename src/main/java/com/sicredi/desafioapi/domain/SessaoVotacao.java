package com.sicredi.desafioapi.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data  
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;

    @OneToMany(mappedBy = "sessaoVotacao", cascade = CascadeType.ALL)
    private List<Voto> votos;


    // Getters e Setters
}
