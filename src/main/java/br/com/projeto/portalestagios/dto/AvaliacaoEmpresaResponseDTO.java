package br.com.projeto.portalestagios.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoEmpresaResponseDTO {

	private Long id;
	
    private Integer nota;
    
    private String comentario;
    
    private Long empresaId;
    
    private String nomeEmpresa;
    
    private Long estudanteId;
    
    private String nomeEstudante;
    
    private LocalDateTime dataAvaliacao;
    
}