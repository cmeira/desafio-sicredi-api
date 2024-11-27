package com.sicredi.desafioapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHoraInclusao;
    
}
