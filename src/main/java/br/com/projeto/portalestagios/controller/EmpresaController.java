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

import br.com.projeto.portalestagios.dto.EmpresaRequestDTO;
import br.com.projeto.portalestagios.dto.EmpresaResponseDTO;
import br.com.projeto.portalestagios.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin
@Tag(name = "Empresa", description = "Operações relacionadas a empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	@PostMapping("/salvar")
	@Operation(summary = "Cria uma nova empresa", description = "Cria uma nova empresa")
	public ResponseEntity<EmpresaResponseDTO> salvar(@Valid @RequestBody EmpresaRequestDTO dto) {
		return ResponseEntity.ok(empresaService.salvar(dto));
	}

	@GetMapping("/listar")
	@PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA', 'ESTUDANTE')")
	@Operation(summary = "Obtém todas as empresas", description = "Retorna uma lista com todas as empresas")
	public ResponseEntity<List<EmpresaResponseDTO>> listar() {
		return ResponseEntity.ok(empresaService.listar());
	}

	@GetMapping("/obter/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA', 'ESTUDANTE')")
	@Operation(summary = "Obtém uma empresa por ID", description = "Retorna uma empresa específica pelo ID")
	public ResponseEntity<EmpresaResponseDTO> pesquisarPorId(@PathVariable Long id) {
		EmpresaResponseDTO EmpresaResponseDTO = empresaService.getEmpresaById(id);
		return ResponseEntity.ok(EmpresaResponseDTO);
	}

	@PutMapping("/alterar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Atualiza uma empresa", description = "Atualiza as informações de uma empresa existente")
	public ResponseEntity<EmpresaResponseDTO> atualizar(@PathVariable Long id,
			@RequestBody EmpresaRequestDTO dto) {
		return ResponseEntity.ok(empresaService.atualizar(id, dto));
	}

	@DeleteMapping("/deletar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Deleta uma empresa", description = "Remove uma empresa do sistema pelo ID")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		empresaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
