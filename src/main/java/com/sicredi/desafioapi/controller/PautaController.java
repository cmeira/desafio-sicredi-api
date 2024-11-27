package com.sicredi.desafioapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sicredi.desafioapi.domain.Pauta;
import com.sicredi.desafioapi.dto.PautaDTO;
import com.sicredi.desafioapi.service.PautaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;

   @PostMapping
    public PautaDTO save(@RequestBody PautaDTO pautaDTO) {
        Pauta pauta = new Pauta();
        pauta.setTitulo(pautaDTO.getTitulo());
        return toDTO(pautaService.salvar(pauta));
    }

    @GetMapping
    public List<PautaDTO> list() {
        return pautaService.listarTodas()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public PautaDTO update(@PathVariable Long id, @RequestBody PautaDTO pautaDTO) {
        Pauta pautaExistente = pautaService.buscarPorId(id);
        pautaExistente.setTitulo(pautaDTO.getTitulo());
        return toDTO(pautaService.salvar(pautaExistente));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pautaService.deletarPorId(id);
    }

    private PautaDTO toDTO(Pauta pauta) {
        PautaDTO dto = new PautaDTO();
        dto.setId(pauta.getId());
        dto.setTitulo(pauta.getTitulo());
        dto.setDescricao(pauta.getDescricao());
        dto.setDataHoraInclusao(pauta.getDataHoraInclusao());
        return dto;
    }
}
