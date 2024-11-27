package com.sicredi.desafioapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicredi.desafioapi.domain.Pauta;
import com.sicredi.desafioapi.repository.PautaRepository;

import java.util.List;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

     // Salvar ou atualizar uma Pauta
     public Pauta salvar(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    // Listar todas as Pautas
    public List<Pauta> listarTodas() {
        return pautaRepository.findAll();
    }

    // Buscar uma Pauta por ID
    public Pauta buscarPorId(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pauta não encontrada para o ID: " + id));
    }

    // Deletar uma Pauta pelo ID
    public void deletarPorId(Long id) {
        Pauta pauta = buscarPorId(id);
        pautaRepository.delete(pauta);
    }
}
