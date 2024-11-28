package com.sicredi.desafioapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sicredi.desafioapi.dto.SessaoVotacaoDTO;
import com.sicredi.desafioapi.model.SessaoVotacao;
import com.sicredi.desafioapi.service.PautaService;
import com.sicredi.desafioapi.service.SessaoVotacaoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sessaovotacao")
public class SessaoVotacaoController {

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    private PautaService pautaService;

   @PostMapping
   public SessaoVotacaoDTO save(@RequestBody SessaoVotacaoDTO dto) {
    SessaoVotacao sessao = new SessaoVotacao();
    // Buscar a Pauta associada ao ID fornecido no DTO
    sessao.setPauta(pautaService.buscarPorId(dto.getPautaId()));
    sessao.setDataAbertura(dto.getDataAbertura());
    sessao.setDataFechamento(dto.getDataFechamento());
    return toDTO(sessaoVotacaoService.salvar(sessao));
}

    @GetMapping
    public List<SessaoVotacaoDTO> list() {
        return sessaoVotacaoService.listarTodas()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public SessaoVotacaoDTO update(@PathVariable Long id, @RequestBody SessaoVotacaoDTO dto) {
        SessaoVotacao sessaoExistente = sessaoVotacaoService.buscarPorId(id);
        sessaoExistente.setDataAbertura(dto.getDataAbertura());
        sessaoExistente.setDataFechamento(dto.getDataFechamento());
        return toDTO(sessaoVotacaoService.salvar(sessaoExistente));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sessaoVotacaoService.deletarPorId(id);
    }

    private SessaoVotacaoDTO toDTO(SessaoVotacao sessao) {
        SessaoVotacaoDTO dto = new SessaoVotacaoDTO();
        dto.setId(sessao.getId());
        dto.setPautaId(sessao.getPauta().getId());
        dto.setDataAbertura(dto.getDataAbertura());
        dto.setDataFechamento(dto.getDataFechamento());
        return dto;
    }
}
