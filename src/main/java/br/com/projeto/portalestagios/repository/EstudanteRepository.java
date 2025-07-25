package br.com.projeto.portalestagios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.portalestagios.entity.Estudante;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {

	boolean existsByEmail(String email);

	boolean existsByCpf(String cpf);
	
	Estudante findByEmail(String email);

	long count();
	
}
