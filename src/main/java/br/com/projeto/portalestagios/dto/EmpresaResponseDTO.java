package br.com.projeto.portalestagios.dto;

import java.util.List;

import lombok.Data;

@Data
public class EmpresaResponseDTO {

	private Long id;
	
	private String nome;
	
	private String cnpj;
	
	private String email;
	
	private String telefone;
	
	private String endereco;
	
	private List<AreaInteresseResponseDTO> areasAtuacao;

}
