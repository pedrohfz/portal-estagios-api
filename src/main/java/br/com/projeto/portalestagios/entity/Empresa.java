package br.com.projeto.portalestagios.entity;

import java.util.List;

import jakarta.persistence.Column;
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
@Table(name = "empresa")
@PrimaryKeyJoinColumn(name = "id")
public class Empresa extends Usuario {

	@Column(nullable = false, unique = true)
	private String cnpj;

	@Column(nullable = false)
	private String telefone;

	private String endereco;

	@ManyToMany
	@JoinTable(name = "empresa_area_atuacao",
			   joinColumns = @JoinColumn(name = "empresa_id"),
			   inverseJoinColumns = @JoinColumn(name = "area_id"))
	private List<AreaInteresse> areasAtuacao;

}
