package com.sicredi.desafioapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
}