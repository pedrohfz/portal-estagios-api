package br.com.projeto.portalestagios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.portalestagios.dto.InscricaoRequestDTO;
import br.com.projeto.portalestagios.dto.InscricaoResponseDTO;
import br.com.projeto.portalestagios.service.InscricaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inscricao")
@CrossOrigin
@Tag(name = "Inscrição", description = "Operações de inscrição de estudantes em vagas")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @PostMapping("/inscrever")
    @PreAuthorize("hasRole('ESTUDANTE')")
    @Operation(summary = "Inscreve um estudante em uma vaga")
    public ResponseEntity<InscricaoResponseDTO> inscrever(@Valid @RequestBody InscricaoRequestDTO dto) {
        return ResponseEntity.ok(inscricaoService.inscrever(dto));
    }

    @GetMapping("/listar-estudante")
    @PreAuthorize("hasRole('ESTUDANTE')")
    @Operation(summary = "Lista vagas em que o estudante está inscrito")
    public ResponseEntity<List<InscricaoResponseDTO>> listarInscricoesDoEstudante() {
        return ResponseEntity.ok(inscricaoService.listarInscricoesDoEstudante());
    }
    
    @GetMapping("/listar-empresa")
    @PreAuthorize("hasRole('EMPRESA')")
    @Operation(summary = "Lista vagas em que o estudante está inscrito")
    public ResponseEntity<List<InscricaoResponseDTO>> listarInscricoesDaEmpresa() {
        return ResponseEntity.ok(inscricaoService.listarInscricoesDaEmpresa());
    }
}
