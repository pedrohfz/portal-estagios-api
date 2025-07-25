package br.com.projeto.portalestagios.entity;

public enum Modalidade {
	REMOTO("Remoto"), PRESENCIAL("Presencial"), HIBRIDO("Híbrido");

	private final String descricao;

	Modalidade(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
