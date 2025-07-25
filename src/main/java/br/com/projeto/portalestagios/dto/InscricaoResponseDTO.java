package br.com.projeto.portalestagios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class InscricaoResponseDTO {
	
    private Long id;
    private Long vagaId;
    private String tituloVaga;
    private String nomeEmpresa;
    private String nomeEstudante;
    private LocalDateTime dataInscricao;

}
