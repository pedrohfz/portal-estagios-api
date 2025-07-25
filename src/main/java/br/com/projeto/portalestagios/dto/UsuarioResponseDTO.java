package br.com.projeto.portalestagios.dto;

import br.com.projeto.portalestagios.entity.Perfil;
import lombok.Data;

@Data
public class UsuarioResponseDTO {

	private Long id;

	private String nome;

	private String email;
	
    private Perfil perfil;
    
}
