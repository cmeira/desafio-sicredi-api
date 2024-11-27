package com.sicredi.desafioapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sicredi.desafioapi.domain.Voto;
import com.sicredi.desafioapi.dto.VotoDTO;
import com.sicredi.desafioapi.service.AssociadoService;
import com.sicredi.desafioapi.service.SessaoVotacaoService;
import com.sicredi.desafioapi.service.VotoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/voto")
public class VotoController {

   @Autowired
    private VotoService votoService;

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    // Salvar um novo Voto
    @PostMapping
    public VotoDTO save(@RequestBody VotoDTO dto) {
        Voto voto = new Voto();
        voto.setSessaoVotacao(sessaoVotacaoService.buscarPorId(dto.getSessaoVotacaoId()));
        voto.setAssociado(associadoService.buscarPorId(dto.getAssociadoId()));
        voto.setVoto(dto.getVoto());
        return toDTO(votoService.salvar(voto));
    }

    // Listar todos os Votos
    @GetMapping
    public List<VotoDTO> list() {
        return votoService.listarTodos()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Atualizar um Voto existente
    @PutMapping("/{id}")
    public VotoDTO update(@PathVariable Long id, @RequestBody VotoDTO dto) {
        Voto votoExistente = votoService.buscarPorId(id);
        votoExistente.setSessaoVotacao(sessaoVotacaoService.buscarPorId(dto.getSessaoVotacaoId()));
        votoExistente.setAssociado(associadoService.buscarPorId(dto.getAssociadoId()));
        votoExistente.setVoto(dto.getVoto());
        return toDTO(votoService.salvar(votoExistente));
    }

    // Deletar um Voto pelo ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        votoService.deletarPorId(id);
    }

    private VotoDTO toDTO(Voto voto) {
        VotoDTO dto = new VotoDTO();
        dto.setId(voto.getId());
        dto.setSessaoVotacaoId(voto.getSessaoVotacao().getId());
        dto.setAssociadoId(voto.getAssociado().getId());
        dto.setVoto(voto.getVoto());
        return dto;
    }
}
