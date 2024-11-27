package com.sicredi.desafioapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sicredi.desafioapi.domain.Associado;
import com.sicredi.desafioapi.dto.AssociadoDTO;
import com.sicredi.desafioapi.service.AssociadoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/associado")
public class AssociadoController {

   @Autowired
   private AssociadoService associadoService;

   @PostMapping
    public AssociadoDTO save(@RequestBody AssociadoDTO dto) {
        Associado associado = new Associado();
        associado.setNome(dto.getNome());
        associado.setCpf(dto.getCpf());
        return toDTO(associadoService.salvar(associado));
    }

    @GetMapping
    public List<AssociadoDTO> list() {
        return associadoService.listarTodos()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public AssociadoDTO update(@PathVariable Long id, @RequestBody AssociadoDTO dto) {
        Associado associadoExistente = associadoService.buscarPorId(id);
        associadoExistente.setNome(dto.getNome());
        associadoExistente.setCpf(dto.getCpf());
        return toDTO(associadoService.salvar(associadoExistente));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        associadoService.deletarPorId(id);
    }

    @GetMapping("/cpf/{cpf}")
    public AssociadoDTO buscarPorCpf(@PathVariable String cpf) {
        Associado associado = associadoService.buscarPorCpf(cpf);
        return toDTO(associado);
    }

    @GetMapping("/email/{email}")
    public AssociadoDTO buscarPorEmail(@PathVariable String email) {
        Associado associado = associadoService.buscarPorEmail(email);
        return toDTO(associado);
    }

    private AssociadoDTO toDTO(Associado associado) {
        AssociadoDTO dto = new AssociadoDTO();
        dto.setId(associado.getId());
        dto.setNome(associado.getNome());
        dto.setCpf(associado.getCpf());
        dto.setEmail(associado.getEmail());
        return dto;
    }
}
     
