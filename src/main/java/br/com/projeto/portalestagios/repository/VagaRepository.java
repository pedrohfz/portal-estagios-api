package br.com.projeto.portalestagios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projeto.portalestagios.entity.SituacaoVaga;
import br.com.projeto.portalestagios.entity.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

    List<Vaga> findByAreaIdIn(List<Long> areaIds);
    
    List<Vaga> findByEmpresaId(Long empresaId);
    
    List<Vaga> findBySituacao(SituacaoVaga situacaoVaga);
    
    List<Vaga> findByAreaIdInAndSituacao(List<Long> areaIds, SituacaoVaga situacao);

    @Query("SELECT v.area.nome, COUNT(v) FROM Vaga v GROUP BY v.area.nome")
    List<Object[]> countVagasByArea();
    
    Long countBySituacao(SituacaoVaga situacao);

}
