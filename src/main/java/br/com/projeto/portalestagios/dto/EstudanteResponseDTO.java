package br.com.projeto.portalestagios.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudanteResponseDTO {

	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String curso;
	private String telefone;
	private List<AreaInteresseResponseDTO> areasInteresse;
}
