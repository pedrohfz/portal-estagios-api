package br.com.projeto.portalestagios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

	@NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail digitado está inválido")
	private String email;

	@NotBlank(message = "A senha é obrigatório.")
	private String senha;

}
