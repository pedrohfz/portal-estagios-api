package br.com.projeto.portalestagios.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscricaoRequestDTO {
    
	@NotNull
    private Long vagaId;

}
