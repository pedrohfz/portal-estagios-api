package br.com.projeto.portalestagios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VagasPorAreaDTO {
	
    private String areaNome;
    
    private Long quantidade;

}