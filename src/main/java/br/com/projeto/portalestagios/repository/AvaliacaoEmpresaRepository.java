package br.com.projeto.portalestagios.repository;

import br.com.projeto.portalestagios.entity.AvaliacaoEmpresa;
import br.com.projeto.portalestagios.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoEmpresaRepository extends JpaRepository<AvaliacaoEmpresa, Long> {

    List<AvaliacaoEmpresa> findByEmpresa(Empresa empresa);

    List<AvaliacaoEmpresa> findByEmpresaId(Long empresaId);

    List<AvaliacaoEmpresa> findByEstudanteId(Long estudanteId);
}