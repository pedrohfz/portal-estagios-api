package br.com.projeto.portalestagios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.portalestagios.entity.Estudante;
import br.com.projeto.portalestagios.entity.Inscricao;
import br.com.projeto.portalestagios.entity.Vaga;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
	
	boolean existsByEstudanteAndVaga(Estudante estudante, Vaga vaga);
	
    List<Inscricao> findByEstudanteId(Long estudanteId);
    
    List<Inscricao> findByEmpresaId(Long empresaId);


}
