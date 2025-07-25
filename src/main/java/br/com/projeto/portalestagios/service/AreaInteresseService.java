package br.com.projeto.portalestagios.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.portalestagios.dto.AreaInteresseRequestDTO;
import br.com.projeto.portalestagios.dto.AreaInteresseResponseDTO;
import br.com.projeto.portalestagios.entity.AreaInteresse;
import br.com.projeto.portalestagios.entity.Empresa;
import br.com.projeto.portalestagios.exception.AreaInteresseJaCadastradaException;
import br.com.projeto.portalestagios.exception.EntityNotFoundException;
import br.com.projeto.portalestagios.mapper.AreaInteresseMapper;
import br.com.projeto.portalestagios.repository.AreaInteresseRepository;
import br.com.projeto.portalestagios.repository.EmpresaRepository;
import br.com.projeto.portalestagios.security.SessaoService;

@Service
public class AreaInteresseService {

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private AreaInteresseRepository areaInteresseRepository;
    
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private AreaInteresseMapper areaInteresseMapper;

    public AreaInteresseResponseDTO salvar(AreaInteresseRequestDTO dto) {
        if (areaInteresseRepository.findByNome(dto.getNome()).isPresent()) {
            throw new AreaInteresseJaCadastradaException(dto.getNome());
        }

        AreaInteresse areaInteresse = areaInteresseRepository.save(areaInteresseMapper.toEntity(dto));
        return areaInteresseMapper.toDto(areaInteresse);
    }
    
    public AreaInteresseResponseDTO getAreaInteresseById(Long id) {
    	AreaInteresse areaInteresse = areaInteresseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Área de Interesse", id));
		return areaInteresseMapper.toDto(areaInteresse);
	}

    public List<AreaInteresseResponseDTO> listar() {
        return areaInteresseRepository.findAll().stream()
                .map(areaInteresseMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AreaInteresseResponseDTO> listarByEmpresa() {
    	
		String usernameLogado = sessaoService.getUsuarioLogado();
		Empresa empresaLogada = empresaRepository.findByEmail(usernameLogado);
		
		List<Long> idsAreasInteresse = empresaLogada.getAreasAtuacao().stream()
                .map(AreaInteresse::getId)
                .collect(Collectors.toList());

        return areaInteresseRepository.findByIdIn(idsAreasInteresse).stream()
                .map(areaInteresseMapper::toDto)
                .collect(Collectors.toList());
    }

    public AreaInteresseResponseDTO atualizar(Long id, AreaInteresseRequestDTO dto) {
        AreaInteresse entity = areaInteresseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Área de Interesse", id));

        entity.setNome(dto.getNome());
        return areaInteresseMapper.toDto(areaInteresseRepository.save(entity));
    }

    public void deletar(Long id) {
        AreaInteresse entity = areaInteresseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Área de Interesse", id));
        areaInteresseRepository.delete(entity);
    }
}
