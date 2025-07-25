package br.com.projeto.portalestagios.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmpresaRequestDTO {
	
    @NotBlank(message = "O nome da empresa é obrigatório")
    private String nome;
    
    @NotBlank(message = "O CNPJ da empresa é obrigatório")
	private String cnpj;
	
    @NotBlank(message = "O e-mail da empresa é obrigatório")
    @Email(message = "O e-mail digitado está inválido")
	private String email;
	
    @NotBlank(message = "A senha é obrigatório.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
	private String senha;
    
    @NotBlank(message = "O telefone da empresa é obrigatório")
	private String telefone;
    
    private String endereco;

    private List<Long> idsAreasAtuacao;
	
}
