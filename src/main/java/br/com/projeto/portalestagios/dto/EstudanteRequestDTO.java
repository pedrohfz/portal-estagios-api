package br.com.projeto.portalestagios.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EstudanteRequestDTO {
	
    @NotBlank(message = "O nome do estudante é obrigatório")
    private String nome;
    
    @NotBlank(message = "O CPF do estudante é obrigatório")
	private String cpf;
	
    @NotBlank(message = "O e-mail do estudante é obrigatório")
    @Email(message = "O e-mail digitado está inválido")
	private String email;
	
    @NotBlank(message = "A senha é obrigatório.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
	private String senha;
    
    @NotBlank(message = "O curso do estudante é obrigatório")
	private String curso;
    
    private List<Long> idsAreasInteresse;
    
}
