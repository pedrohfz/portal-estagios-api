package br.com.projeto.portalestagios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.portalestagios.dto.AvaliacaoEmpresaRequestDTO;
import br.com.projeto.portalestagios.entity.AvaliacaoEmpresa;
import br.com.projeto.portalestagios.service.AvaliacaoEmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/avaliar-empresa")
@CrossOrigin
@Tag(name = "Avaliar Empresa", description = "Operações relacionadas a avaliação da empresa")
public class AvaliacaoEmpresaController {

	@Autowired
	private AvaliacaoEmpresaService avaliacaoEmpresaService;

	@PostMapping("/salvar")
	@PreAuthorize("hasRole('ESTUDANTE')")
	@Operation(summary = "Cria uma nova avaliação", description = "Cria uma nova avaliação para empresa")
	public ResponseEntity<AvaliacaoEmpresa> salvar(@Valid @RequestBody AvaliacaoEmpresaRequestDTO dto) {
		return ResponseEntity.ok(avaliacaoEmpresaService.salvar(dto));
	}

	@GetMapping("/listar/{id}")
	@PreAuthorize("hasRole('ESTUDANTE')")
    @Operation(summary = "Obtém todas as avaliações por empresa", description = "Retorna uma lista de avaliações por empresa")
    public ResponseEntity<List<AvaliacaoEmpresa>> getAvaliacoesPorEmpresa(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoEmpresaService.buscarAvaliacoesPorEmpresa(id));
    }

}
