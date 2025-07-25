package br.com.projeto.portalestagios.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projeto.portalestagios.dto.EstudanteRequestDTO;
import br.com.projeto.portalestagios.dto.EstudanteResponseDTO;
import br.com.projeto.portalestagios.entity.AreaInteresse;
import br.com.projeto.portalestagios.entity.Estudante;
import br.com.projeto.portalestagios.entity.Perfil;
import br.com.projeto.portalestagios.exception.EntityNotFoundException;
import br.com.projeto.portalestagios.exception.ObjetoCadastradoException;
import br.com.projeto.portalestagios.mapper.EstudanteMapper;
import br.com.projeto.portalestagios.repository.AreaInteresseRepository;
import br.com.projeto.portalestagios.repository.EstudanteRepository;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository estudanteRepository;

    @Autowired
    private AreaInteresseRepository areaInteresseRepository;

    @Autowired
    private EstudanteMapper estudanteMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public EstudanteResponseDTO salvar(EstudanteRequestDTO dto) {

        if (estudanteRepository.existsByCpf(dto.getCpf())) {
            throw new ObjetoCadastradoException("Já existe estudante cadastrado com o CPF: " + dto.getCpf());
        }

        if (estudanteRepository.existsByEmail(dto.getEmail())) {
            throw new ObjetoCadastradoException("Já existe estudante cadastrado com o e-mail: " + dto.getEmail());
        }

        dto.setSenha(passwordEncoder.encode(dto.getSenha()));

        Estudante estudante = estudanteMapper.toEntity(dto);
        estudante.setPerfil(Perfil.ESTUDANTE);

        List<AreaInteresse> areas = areaInteresseRepository.findAllById(dto.getIdsAreasInteresse());
        estudante.setAreasInteresse(areas);

        estudante = estudanteRepository.save(estudante);

        return estudanteMapper.toDto(estudante);
    }

    public EstudanteResponseDTO getEstudanteById(Long id) {
        Estudante estudante = estudanteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudante", id));
        return estudanteMapper.toDto(estudante);
    }

    public List<EstudanteResponseDTO> listar() {
        return estudanteRepository.findAll().stream().map(estudanteMapper::toDto).collect(Collectors.toList());
    }

    public EstudanteResponseDTO atualizar(Long id, EstudanteRequestDTO dto) {
        Estudante entity = estudanteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudante", id));

        entity.setNome(dto.getNome());

        List<AreaInteresse> areas = areaInteresseRepository.findAllById(dto.getIdsAreasInteresse());
        entity.setAreasInteresse(areas);

        return estudanteMapper.toDto(estudanteRepository.save(entity));
    }

    public void deletar(Long id) {
        Estudante entity = estudanteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudante", id));
        estudanteRepository.delete(entity);
    }
}
