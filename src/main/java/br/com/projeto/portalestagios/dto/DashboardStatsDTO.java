package br.com.projeto.portalestagios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDTO {
	
    private Long totalEmpresas;
    
    private Long totalEstudantes;
    
    private Long totalVagasAbertas;
    
    private Long totalVagasEncerradas;

}