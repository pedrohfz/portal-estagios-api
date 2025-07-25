package br.com.projeto.portalestagios.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.portalestagios.entity.AreaInteresse;

public interface AreaInteresseRepository extends JpaRepository<AreaInteresse, Long> {
	
    Optional<AreaInteresse> findByNome(String nome);

    List<AreaInteresse> findByIdIn(List<Long> ids);

}
