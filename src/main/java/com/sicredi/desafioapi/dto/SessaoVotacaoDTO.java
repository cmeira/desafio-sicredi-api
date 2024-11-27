package com.sicredi.desafioapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacaoDTO {
    private Long id;
    private Long pautaId; // Relacionamento com Pauta
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
}
