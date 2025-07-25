package br.com.projeto.portalestagios.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.portalestagios.dto.DashboardDTO;
import br.com.projeto.portalestagios.dto.DashboardStatsDTO;
import br.com.projeto.portalestagios.dto.VagasPorAreaDTO;
import br.com.projeto.portalestagios.entity.SituacaoVaga;
import br.com.projeto.portalestagios.repository.EmpresaRepository;
import br.com.projeto.portalestagios.repository.EstudanteRepository;
import br.com.projeto.portalestagios.repository.VagaRepository;

@Service
public class DashboardService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EstudanteRepository estudanteRepository;

    @Autowired
    private VagaRepository vagaRepository;

    public DashboardDTO getDashboardData() {
        // 1. Coletar as estatísticas gerais
        Long totalEmpresas = empresaRepository.count();
        Long totalEstudantes = estudanteRepository.count();
        Long totalVagasAbertas = vagaRepository.countBySituacao(SituacaoVaga.ABERTA);
        Long totalVagasEncerradas = vagaRepository.countBySituacao(SituacaoVaga.ENCERRADA);

        DashboardStatsDTO stats = new DashboardStatsDTO(
            totalEmpresas,
            totalEstudantes,
            totalVagasAbertas,
            totalVagasEncerradas
        );

        // 2. Coletar os dados para o gráfico de vagas por área
        List<Object[]> vagasPorAreaRaw = vagaRepository.countVagasByArea();
        List<VagasPorAreaDTO> vagasPorArea = vagasPorAreaRaw.stream()
            .map(result -> new VagasPorAreaDTO((String) result[0], (Long) result[1]))
            .collect(Collectors.toList());

        // 3. Montar o DTO final do dashboard
        return new DashboardDTO(stats, vagasPorArea);
    }
}