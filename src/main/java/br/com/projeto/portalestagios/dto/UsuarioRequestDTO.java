package br.com.projeto.portalestagios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRequestDTO {

	@NotBlank(message = "O nome é obrigatório.")
	private String nome;

	@NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail digitado está inválido")
	private String email;

	@NotBlank(message = "A senha é obrigatório.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
	private String senha;

}
