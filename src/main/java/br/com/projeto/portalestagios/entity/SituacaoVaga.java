package br.com.projeto.portalestagios.entity;

public enum SituacaoVaga {
	ABERTA("Aberta"), ENCERRADA("Encerrada");

	private final String descricao;

	SituacaoVaga(String descricao) {
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
