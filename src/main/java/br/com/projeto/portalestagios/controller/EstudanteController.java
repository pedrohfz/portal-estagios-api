package br.com.projeto.portalestagios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.portalestagios.dto.EstudanteRequestDTO;
import br.com.projeto.portalestagios.dto.EstudanteResponseDTO;
import br.com.projeto.portalestagios.service.EstudanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estudante")
@CrossOrigin
@Tag(name = "Estudante", description = "Operações relacionadas a estudante")
public class EstudanteController {

	@Autowired
	private EstudanteService estudanteService;

	@PostMapping("/salvar")
	@Operation(summary = "Cria um novo estudante", description = "Cria um novo estudante")
	public ResponseEntity<EstudanteResponseDTO> salvar(@Valid @RequestBody EstudanteRequestDTO dto) {
		return ResponseEntity.ok(estudanteService.salvar(dto));
	}

	@GetMapping("/listar")
	@Operation(summary = "Obtém todas os estudantes", description = "Retorna uma lista com todos os estudantes")
	public ResponseEntity<List<EstudanteResponseDTO>> listar() {
		return ResponseEntity.ok(estudanteService.listar());
	}

	@GetMapping("/obter/{id}")
	@Operation(summary = "Obtém um estudante por ID", description = "Retorna um estudante específica pelo ID")
	public ResponseEntity<EstudanteResponseDTO> pesquisarPorId(@PathVariable Long id) {
		EstudanteResponseDTO EstudanteResponseDTO = estudanteService.getEstudanteById(id);
		return ResponseEntity.ok(EstudanteResponseDTO);
	}

	@PutMapping("/alterar/{id}")
	@Operation(summary = "Atualiza um estudante", description = "Atualiza as informações de um estudante existente")
	public ResponseEntity<EstudanteResponseDTO> atualizar(@PathVariable Long id,
			@RequestBody EstudanteRequestDTO dto) {
		return ResponseEntity.ok(estudanteService.atualizar(id, dto));
	}

	@DeleteMapping("/deletar/{id}")
	@Operation(summary = "Deleta um estudante", description = "Remove um estudante do sistema pelo ID")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		estudanteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
