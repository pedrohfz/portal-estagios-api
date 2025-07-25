package br.com.projeto.portalestagios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.portalestagios.entity.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	boolean existsByEmail(String email);
    
	boolean existsByCnpj(String cnpj);
	
	Empresa findByEmail(String email);
	
    long count();
	
}
