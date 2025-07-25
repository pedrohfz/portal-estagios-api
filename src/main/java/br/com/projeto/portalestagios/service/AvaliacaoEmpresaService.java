package br.com.projeto.portalestagios.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.portalestagios.dto.AvaliacaoEmpresaRequestDTO;
import br.com.projeto.portalestagios.entity.AvaliacaoEmpresa;
import br.com.projeto.portalestagios.entity.Empresa;
import br.com.projeto.portalestagios.entity.Estudante;
import br.com.projeto.portalestagios.exception.EntityNotFoundException;
import br.com.projeto.portalestagios.repository.AvaliacaoEmpresaRepository;
import br.com.projeto.portalestagios.repository.EmpresaRepository; // Assumindo que você tem um EmpresaRepository
import br.com.projeto.portalestagios.repository.EstudanteRepository;
import br.com.projeto.portalestagios.security.SessaoService;

@Service
public class AvaliacaoEmpresaService {

	@Autowired
    private SessaoService sessaoService;
	
    @Autowired
    private AvaliacaoEmpresaRepository avaliacaoEmpresaRepository;

    @Autowired
    private EmpresaRepository empresaRepository; 

    @Autowired
    private EstudanteRepository estudanteRepository;
    
    public AvaliacaoEmpresa salvar(AvaliacaoEmpresaRequestDTO dto) {

        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new EntityNotFoundException("Empresa", dto.getEmpresaId()));

		String usernameLogado = sessaoService.getUsuarioLogado();
		Estudante estudanteLogado = estudanteRepository.findByEmail(usernameLogado);
		
        AvaliacaoEmpresa avaliacao = new AvaliacaoEmpresa();
        avaliacao.setEmpresa(empresa);
        avaliacao.setEstudante(estudanteLogado);
        avaliacao.setNota(dto.getNota());
        avaliacao.setComentario(dto.getComentario());
        avaliacao.setDataAvaliacao(LocalDateTime.now());

        return avaliacaoEmpresaRepository.save(avaliacao);
    }

    public List<AvaliacaoEmpresa> buscarAvaliacoesPorEmpresa(Long empresaId) {
        return avaliacaoEmpresaRepository.findByEmpresaId(empresaId);
    }

    public Optional<AvaliacaoEmpresa> buscarAvaliacaoPorId(Long id) {
        return avaliacaoEmpresaRepository.findById(id);
    }

    @Transactional
    public AvaliacaoEmpresa atualizarAvaliacao(Long id, Integer novaNota, String novoComentario) {
        Optional<AvaliacaoEmpresa> avaliacaoOpt = avaliacaoEmpresaRepository.findById(id);
        if (avaliacaoOpt.isEmpty()) {
            throw new IllegalArgumentException("Avaliação não encontrada com o ID: " + id);
        }

        AvaliacaoEmpresa avaliacao = avaliacaoOpt.get();
        if (novaNota != null) {
            if (novaNota < 1 || novaNota > 5) {
                throw new IllegalArgumentException("A nota deve estar entre 1 e 5.");
            }
            avaliacao.setNota(novaNota);
        }
        if (novoComentario != null) {
            avaliacao.setComentario(novoComentario);
        }
        // Não atualizamos a empresa ou o avaliador, apenas nota e comentário.
        // Se precisar, adicione lógica para isso.

        return avaliacaoEmpresaRepository.save(avaliacao);
    }

    @Transactional
    public void deletarAvaliacao(Long id) {
        if (!avaliacaoEmpresaRepository.existsById(id)) {
            throw new IllegalArgumentException("Avaliação não encontrada com o ID: " + id);
        }
        avaliacaoEmpresaRepository.deleteById(id);
    }

    // Método para calcular a média de avaliações de uma empresa
    public Double calcularMediaAvaliacoes(Long empresaId) {
        List<AvaliacaoEmpresa> avaliacoes = avaliacaoEmpresaRepository.findByEmpresaId(empresaId);
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }
        return avaliacoes.stream()
                .mapToInt(AvaliacaoEmpresa::getNota)
                .average()
                .orElse(0.0);
    }
}