package br.com.projeto.portalestagios.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projeto.portalestagios.dto.EmpresaRequestDTO;
import br.com.projeto.portalestagios.dto.EmpresaResponseDTO;
import br.com.projeto.portalestagios.entity.AreaInteresse;
import br.com.projeto.portalestagios.entity.Empresa;
import br.com.projeto.portalestagios.entity.Perfil;
import br.com.projeto.portalestagios.exception.EntityNotFoundException;
import br.com.projeto.portalestagios.exception.ObjetoCadastradoException;
import br.com.projeto.portalestagios.mapper.EmpresaMapper;
import br.com.projeto.portalestagios.repository.AreaInteresseRepository;
import br.com.projeto.portalestagios.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private AreaInteresseRepository areaInteresseRepository;

	@Autowired
	private EmpresaMapper empresaMapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public EmpresaResponseDTO salvar(EmpresaRequestDTO empresaRequestDTO) {

		if (empresaRepository.existsByCnpj(empresaRequestDTO.getCnpj())) {
			throw new ObjetoCadastradoException(
					"Já existe empresa cadastrada para o CNPJ: " + empresaRequestDTO.getCnpj());
		}

		if (empresaRepository.existsByEmail(empresaRequestDTO.getEmail())) {
			throw new ObjetoCadastradoException(
					"Já existe empresa cadastrada para o e-mail: " + empresaRequestDTO.getEmail());
		}

		empresaRequestDTO.setSenha(passwordEncoder.encode(empresaRequestDTO.getSenha()));

		Empresa empresa = empresaMapper.toEntity(empresaRequestDTO);
		empresa.setPerfil(Perfil.EMPRESA);

		List<AreaInteresse> areas = areaInteresseRepository.findAllById(empresaRequestDTO.getIdsAreasAtuacao());
		empresa.setAreasAtuacao(areas);

		empresa = empresaRepository.save(empresa);
		
		return empresaMapper.toDto(empresa);
	}

	public EmpresaResponseDTO getEmpresaById(Long id) {
		Empresa empresa = empresaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Empresa", id));
		return empresaMapper.toDto(empresa);
	}

	public List<EmpresaResponseDTO> listar() {
		return empresaRepository.findAll().stream().map(empresaMapper::toDto).collect(Collectors.toList());
	}

	public EmpresaResponseDTO atualizar(Long id, EmpresaRequestDTO dto) {
		Empresa entity = empresaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Empresa", id));

		entity.setNome(dto.getNome());
		entity.setTelefone(dto.getTelefone());
		entity.setEndereco(dto.getEndereco());

		if (dto.getIdsAreasAtuacao() != null) {
			List<AreaInteresse> areas = areaInteresseRepository.findAllById(dto.getIdsAreasAtuacao());
			entity.setAreasAtuacao(areas);
		}
		
		return empresaMapper.toDto(empresaRepository.save(entity));
	}

	public void deletar(Long id) {
		Empresa entity = empresaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Empresa", id));
		empresaRepository.delete(entity);
	}
}
