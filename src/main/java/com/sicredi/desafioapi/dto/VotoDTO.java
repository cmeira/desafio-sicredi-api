package com.sicredi.desafioapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO {
    private Long id;
    private Long sessaoVotacaoId; // Relacionamento com SessaoVotacao
    private Long associadoId;     // Relacionamento com Associado
    private String voto;   
}
