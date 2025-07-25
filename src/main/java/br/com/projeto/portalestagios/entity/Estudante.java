package br.com.projeto.portalestagios.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estudante")
@PrimaryKeyJoinColumn(name = "id") // Especifica a coluna de junção com a tabela usuario
public class Estudante extends Usuario {

	private String cpf;

	private String curso;

	@ManyToMany
	@JoinTable(name = "estudante_area_interesse",
			   joinColumns = @JoinColumn(name = "estudante_id"),
			   inverseJoinColumns = @JoinColumn(name = "area_id"))
	private List<AreaInteresse> areasInteresse;

}
