package br.com.projeto.portalestagios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
	
    private DashboardStatsDTO stats;
    
    private List<VagasPorAreaDTO> vagasPorArea;

}