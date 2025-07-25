package br.com.projeto.portalestagios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.projeto.portalestagios.dto.VagaRequestDTO;
import br.com.projeto.portalestagios.dto.VagaResponseDTO;
import br.com.projeto.portalestagios.service.VagaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vaga")
@CrossOrigin
@Tag(name = "Vaga", description = "Operações relacionadas a vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @PostMapping("/salvar")
    @PreAuthorize("hasRole('EMPRESA')")
    @Operation(summary = "Cria uma nova vaga", description = "Cria uma nova vaga")
    public ResponseEntity<VagaResponseDTO> salvar(@Valid @RequestBody VagaRequestDTO dto) {
        return ResponseEntity.ok(vagaService.salvar(dto));
    }
    
    @GetMapping("/listar")
    @PreAuthorize("hasRole('EMPRESA')")
    @Operation(summary = "Lista todas as vagas", description = "Retorna todas as vagas cadastradas")
    public ResponseEntity<List<VagaResponseDTO>> listar() {
        return ResponseEntity.ok(vagaService.listar());
    }
    
    @GetMapping("/listar-empresa")
    @PreAuthorize("hasRole('EMPRESA')")
    @Operation(summary = "Lista todas as vagas", description = "Retorna todas as vagas cadastradas da empresa logada")
    public ResponseEntity<List<VagaResponseDTO>> listarByEmpresa() {
        return ResponseEntity.ok(vagaService.listarByEmpresa());
    }
    
    @GetMapping("/listar-disponiveis")
	@PreAuthorize("hasAnyRole('ADMIN', 'ESTUDANTE')")
    @Operation(summary = "Lista todas as vagas disponíveis", description = "Retorna todas as vagas disponíveis para os estudantes")
    public ResponseEntity<List<VagaResponseDTO>> listarDisponiveis() {
        return ResponseEntity.ok(vagaService.listarVagasPorInteressesDoEstudante());
    }

    @GetMapping("/obter/{id}")
	@PreAuthorize("hasAnyRole('EMPRESA', 'ESTUDANTE')")
    @Operation(summary = "Obtém uma vaga por ID", description = "Retorna os dados de uma vaga específica")
    public ResponseEntity<VagaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vagaService.getVagaById(id));
    }

    @GetMapping("/encerrar/{id}")
    @PreAuthorize("hasRole('EMPRESA')")
    @Operation(summary = "Encerra uma vaga", description = "Encerra uma vaga")
    public ResponseEntity<VagaResponseDTO> encerrar(@PathVariable Long id) {
        return ResponseEntity.ok(vagaService.encerrar(id));
    }
    
    @PutMapping("/alterar/{id}")
    @PreAuthorize("hasRole('EMPRESA')")
    @Operation(summary = "Atualiza uma vaga", description = "Atualiza os dados de uma vaga existente")
    public ResponseEntity<VagaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody VagaRequestDTO dto) {
        return ResponseEntity.ok(vagaService.atualizar(id, dto));
    }

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasRole('EMPRESA')")
    @Operation(summary = "Deleta uma vaga", description = "Remove uma vaga pelo ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vagaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
