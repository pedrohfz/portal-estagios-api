package br.com.projeto.portalestagios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.projeto.portalestagios.dto.DashboardDTO;
import br.com.projeto.portalestagios.dto.UsuarioRequestDTO;
import br.com.projeto.portalestagios.dto.UsuarioResponseDTO;
import br.com.projeto.portalestagios.service.DashboardService;
import br.com.projeto.portalestagios.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Usuário", description = "Operações relacionadas ao Usuário")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private DashboardService dashboardService;

	@GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Dados para o dashboard", description = "Retorna os dados para gerar o dashboard")
    public ResponseEntity<DashboardDTO> getDashboardData() {
        DashboardDTO dashboardData = dashboardService.getDashboardData();
        return ResponseEntity.ok(dashboardData);
    }
    
	@PostMapping("/salvar")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Cria um novo usuario", description = "Cria um novo usuário no sistema")
	public ResponseEntity<UsuarioResponseDTO> salvar(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
		UsuarioResponseDTO usuarioResponseDTO = usuarioService.salvar(usuarioRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponseDTO);
	}
	
	@GetMapping("/listar")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Obtém todos os usuarios", description = "Retorna uma lista com todos os usuários cadastrados")
	public ResponseEntity<List<UsuarioResponseDTO>> listar() {
		List<UsuarioResponseDTO> usuarios = usuarioService.getAllUsuarios();
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping("/obter/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Obtém um usuario por ID", description = "Retorna um usuário específico pelo ID")
	public ResponseEntity<UsuarioResponseDTO> pesquisarPorId(@PathVariable Long id) {
		UsuarioResponseDTO usuarioResponseDTO = usuarioService.getUsuarioById(id);
		return ResponseEntity.ok(usuarioResponseDTO);
	}

	@PutMapping("/alterar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Atualiza um usuario", description = "Atualiza as informações de um usuário existente")
	public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
		UsuarioResponseDTO usuarioResponseDTO = usuarioService.atualizar(id, usuarioRequestDTO);
		return ResponseEntity.ok(usuarioResponseDTO);
	}

	@DeleteMapping("/deletar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Deleta um usuario", description = "Remove um usuário do sistema pelo ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
	
}
