package br.com.projeto.portalestagios.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.portalestagios.dto.VagaRequestDTO;
import br.com.projeto.portalestagios.dto.VagaResponseDTO;
import br.com.projeto.portalestagios.entity.AreaInteresse;
import br.com.projeto.portalestagios.entity.Empresa;
import br.com.projeto.portalestagios.entity.Estudante;
import br.com.projeto.portalestagios.entity.SituacaoVaga;
import br.com.projeto.portalestagios.entity.Vaga;
import br.com.projeto.portalestagios.exception.EntityNotFoundException;
import br.com.projeto.portalestagios.mapper.VagaMapper;
import br.com.projeto.portalestagios.repository.AreaInteresseRepository;
import br.com.projeto.portalestagios.repository.EmpresaRepository;
import br.com.projeto.portalestagios.repository.EstudanteRepository;
import br.com.projeto.portalestagios.repository.VagaRepository;
import br.com.projeto.portalestagios.security.SessaoService;

@Service
public class VagaService {

    @Autowired
    private SessaoService sessaoService;
    
    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private AreaInteresseRepository areaRepository;
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    @Autowired
    private EstudanteRepository estudanteRepository;
    
    @Autowired
    private VagaMapper vagaMapper;
    
    public VagaResponseDTO salvar(VagaRequestDTO dto) {
    	
		Vaga vaga= vagaMapper.toEntity(dto);

        vaga.setEmpresa(this.empresaLogada());
        vaga.setSituacao(SituacaoVaga.ABERTA);
        
        AreaInteresse area = areaRepository.findById(dto.getAreaId())
                .orElseThrow(() -> new EntityNotFoundException("Area", dto.getAreaId()));
        vaga.setArea(area);

        vaga = vagaRepository.save(vaga);

        return vagaMapper.toDto(vaga);
    }

    public List<VagaResponseDTO> listar() {
		return vagaRepository.findAll().stream().map(vagaMapper::toDto).collect(Collectors.toList());
	}
    
	private Empresa empresaLogada() {
		String usernameLogado = sessaoService.getUsuarioLogado();
		return empresaRepository.findByEmail(usernameLogado);
	}
    
	public List<VagaResponseDTO> listarByEmpresa() {
		return vagaRepository.findByEmpresaId(this.empresaLogada().getId()).stream().map(vagaMapper::toDto)
				.collect(Collectors.toList());
	}
    
	public List<VagaResponseDTO> listarVagasPorInteressesDoEstudante() {
        
		String usernameLogado = sessaoService.getUsuarioLogado();
        Estudante estudante = estudanteRepository.findByEmail(usernameLogado);

        List<Long> idsAreasInteresse = estudante.getAreasInteresse().stream()
                .map(AreaInteresse::getId)
                .collect(Collectors.toList());

        if (idsAreasInteresse.isEmpty()) {
            return List.of();
        }

        return vagaRepository.findByAreaIdInAndSituacao(idsAreasInteresse, SituacaoVaga.ABERTA)
                .stream().map(vagaMapper::toDto).collect(Collectors.toList());
    }

    public VagaResponseDTO getVagaById(Long id) {
		Vaga vaga= vagaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vaga", id));
		return vagaMapper.toDto(vaga);
	}
    
    public VagaResponseDTO atualizar(Long id, VagaRequestDTO dto) {
		Vaga vaga = vagaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Vaga", id));
		
		AreaInteresse area = areaRepository.findById(dto.getAreaId())
                .orElseThrow(() -> new EntityNotFoundException("Area", dto.getAreaId()));
        
		vaga.setTitulo(dto.getTitulo());
        vaga.setDescricao(dto.getDescricao());
        vaga.setLocalizacao(dto.getLocalizacao());
        vaga.setModalidade(dto.getModalidade());
        vaga.setCargaHoraria(dto.getCargaHoraria());
        vaga.setRequisitos(dto.getRequisitos());
        vaga.setArea(area);

		return vagaMapper.toDto(vagaRepository.save(vaga));
	}
    
    public VagaResponseDTO encerrar(Long id) {
		Vaga vaga = vagaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Vaga", id));
		
		vaga.setSituacao(SituacaoVaga.ENCERRADA);

		return vagaMapper.toDto(vagaRepository.save(vaga));
	}
    
    public void deletar(Long id) {
		Vaga vaga = vagaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Vaga", id));
		vagaRepository.delete(vaga);
	}

}
