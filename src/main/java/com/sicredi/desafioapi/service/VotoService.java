package com.sicredi.desafioapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sicredi.desafioapi.repository.VotoRepository;
import com.sicredi.desafioapi.domain.SessaoVotacao;
import com.sicredi.desafioapi.domain.Voto;
import java.util.List;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;


    // Salvar ou registrar um Voto
    public Voto salvar(Voto voto) {
        return votoRepository.save(voto);
    }

    // Listar todos os Votos
    public List<Voto> listarTodos() {
        return votoRepository.findAll();
    }

    // Buscar um Voto por ID
    public Voto buscarPorId(Long id) {
        return votoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Voto não encontrado para o ID: " + id));
    }

    // Deletar um Voto pelo ID
    public void deletarPorId(Long id) {
        Voto voto = buscarPorId(id);
        votoRepository.delete(voto);
    }

    public Voto registrarVoto(Voto voto) {
        // Garante que um associado só possa votar uma vez na mesma sessão
        if (votoRepository.findBySessaoVotacao(voto.getSessaoVotacao()).stream()
                .anyMatch(v -> v.getAssociado().getId().equals(voto.getAssociado().getId()))) {
            throw new IllegalArgumentException("Associado já votou nesta sessão.");
        }
        return votoRepository.save(voto);
    }
    
    // Listar todos os Votos associados a uma Sessão de Votação
    public List<Voto> listarVotosPorSessao(SessaoVotacao sessaoVotacao) {
        return votoRepository.findBySessaoVotacao(sessaoVotacao);
    }

    public long contarVotosSim(SessaoVotacao sessaoVotacao) {
        return votoRepository.findBySessaoVotacao(sessaoVotacao).stream()
                .filter(v -> "Sim".equalsIgnoreCase(v.getVoto()))
                .count();
    }

    public long contarVotosNao(SessaoVotacao sessaoVotacao) {
        return votoRepository.findBySessaoVotacao(sessaoVotacao).stream()
                .filter(v -> "Não".equalsIgnoreCase(v.getVoto()))
                .count();
    }
    
}
