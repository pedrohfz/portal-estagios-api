package br.com.projeto.portalestagios.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AreaInteresseRequestDTO {
	
    @NotBlank(message = "O nome da área de interesse é obrigatório")
    private String nome;
    
}
