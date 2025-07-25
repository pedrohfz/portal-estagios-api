package br.com.projeto.portalestagios.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.portalestagios.dto.InscricaoRequestDTO;
import br.com.projeto.portalestagios.dto.InscricaoResponseDTO;
import br.com.projeto.portalestagios.entity.Empresa;
import br.com.projeto.portalestagios.entity.Estudante;
import br.com.projeto.portalestagios.entity.Inscricao;
import br.com.projeto.portalestagios.entity.SituacaoVaga;
import br.com.projeto.portalestagios.entity.Vaga;
import br.com.projeto.portalestagios.exception.InscritoVagaException;
import br.com.projeto.portalestagios.exception.VagaEncerradaException;
import br.com.projeto.portalestagios.mapper.InscricaoMapper;
import br.com.projeto.portalestagios.repository.EmpresaRepository;
import br.com.projeto.portalestagios.repository.EstudanteRepository;
import br.com.projeto.portalestagios.repository.InscricaoRepository;
import br.com.projeto.portalestagios.repository.VagaRepository;
import br.com.projeto.portalestagios.security.SessaoService;

@Service
public class InscricaoService {

	@Autowired
	private SessaoService sessaoService;

	@Autowired
	private VagaRepository vagaRepository;

	@Autowired
	private EstudanteRepository estudanteRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private InscricaoRepository inscricaoRepository;

	@Autowired
	private InscricaoMapper inscricaoMapper;

	public InscricaoResponseDTO inscrever(InscricaoRequestDTO dto) {

		String usernameLogado = sessaoService.getUsuarioLogado();
		Estudante estudanteLogado = estudanteRepository.findByEmail(usernameLogado);

		Vaga vaga = vagaRepository.findById(dto.getVagaId()).orElseThrow();

		if (vaga.getSituacao() == SituacaoVaga.ENCERRADA) {
			throw new VagaEncerradaException("Não é possível se inscrever em uma vaga encerrada.");
		}

		boolean jaInscrito = inscricaoRepository.existsByEstudanteAndVaga(estudanteLogado, vaga);
		if (jaInscrito) {
			throw new InscritoVagaException("Você já está inscrito nesta vaga.");
		}

		Inscricao inscricao = new Inscricao();
		inscricao.setEstudante(estudanteLogado);
		inscricao.setVaga(vaga);
		inscricao.setEmpresa(vaga.getEmpresa());
		inscricao.setDataInscricao(LocalDateTime.now());

		return inscricaoMapper.toDto(inscricaoRepository.save(inscricao));
	}

	public List<InscricaoResponseDTO> listarInscricoesDoEstudante() {

		String usernameLogado = sessaoService.getUsuarioLogado();
		Estudante estudanteLogado = estudanteRepository.findByEmail(usernameLogado);

		return inscricaoRepository.findByEstudanteId(estudanteLogado.getId()).stream().map(inscricaoMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public List<InscricaoResponseDTO> listarInscricoesDaEmpresa() {

		String usernameLogado = sessaoService.getUsuarioLogado();
		Empresa empresaLogada = empresaRepository.findByEmail(usernameLogado);

		return inscricaoRepository.findByEmpresaId(empresaLogada.getId()).stream().map(inscricaoMapper::toDto)
				.collect(Collectors.toList());
	}
	
}
