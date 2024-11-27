package com.sicredi.desafioapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sicredi.desafioapi.repository.AssociadoRepository;
import com.sicredi.desafioapi.domain.Associado;

import java.util.List;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    // Salvar ou atualizar um Associado
    public Associado salvar(Associado associado) {
        return associadoRepository.save(associado);
    }

    // Listar todos os Associados
    public List<Associado> listarTodos() {
        return associadoRepository.findAll();
    }

    // Buscar um Associado por ID
    public Associado buscarPorId(Long id) {
        return associadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Associado não encontrado para o ID: " + id));
    }

    // Deletar um Associado pelo ID
    public void deletarPorId(Long id) {
        Associado associado = buscarPorId(id);
        associadoRepository.delete(associado);
    }
    public Associado buscarPorCpf(String cpf) {
    return associadoRepository.findByCpf(cpf);
}

public Associado buscarPorEmail(String email) {
    return associadoRepository.findByEmail(email);
}
}
