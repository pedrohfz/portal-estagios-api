package br.com.projeto.portalestagios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.portalestagios.dto.AreaInteresseRequestDTO;
import br.com.projeto.portalestagios.dto.AreaInteresseResponseDTO;
import br.com.projeto.portalestagios.service.AreaInteresseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/area-interesse")
@CrossOrigin
@Tag(name = "Área de Interesse", description = "Operações relacionadas a área de interesse")
public class AreaInteresseController {

	@Autowired
	private AreaInteresseService areaInteresseService;

	@PostMapping("/salvar")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Cria uma nova área de interesse", description = "Cria uma nova área de interesse")
	public ResponseEntity<AreaInteresseResponseDTO> salvar(@Valid @RequestBody AreaInteresseRequestDTO dto) {
		return ResponseEntity.ok(areaInteresseService.salvar(dto));
	}

	@GetMapping("/listar")
	@Operation(summary = "Obtém todas as áreas de interesse", description = "Retorna uma lista com todas as áreas de interesse")
	public ResponseEntity<List<AreaInteresseResponseDTO>> listar() {
		return ResponseEntity.ok(areaInteresseService.listar());
	}

	@GetMapping("/listar-areas-empresa")
	@Operation(summary = "Obtém todas as áreas de interesse da empresa", description = "Retorna uma lista com todas as áreas de interesse da empresa")
	public ResponseEntity<List<AreaInteresseResponseDTO>> listarAreasEmpresa() {
		return ResponseEntity.ok(areaInteresseService.listarByEmpresa());
	}
	
	@GetMapping("/obter/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'ESTUDANTE', 'EMPRESA')")
	@Operation(summary = "Obtém uma área de interesse por ID", description = "Retorna uma área de interesse específica pelo ID")
	public ResponseEntity<AreaInteresseResponseDTO> pesquisarPorId(@PathVariable Long id) {
		AreaInteresseResponseDTO areaInteresseResponseDTO = areaInteresseService.getAreaInteresseById(id);
		return ResponseEntity.ok(areaInteresseResponseDTO);
	}

	@PutMapping("/alterar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Atualiza uma área de interesse", description = "Atualiza as informações de uma área de interesse existente")
	public ResponseEntity<AreaInteresseResponseDTO> atualizar(@PathVariable Long id,
			@RequestBody AreaInteresseRequestDTO dto) {
		return ResponseEntity.ok(areaInteresseService.atualizar(id, dto));
	}

	@DeleteMapping("/deletar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Deleta uma área de interesse", description = "Remove uma área de interesse do sistema pelo ID")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		areaInteresseService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
