package br.com.projeto.portalestagios.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String entityName, Long id) {
		super(String.format("%s não encontrado com o ID: %d", entityName, id));
	}
	
	public EntityNotFoundException(String email) {
		super(String.format("Usuáiro não encontrado com o e-mail: %s", email));
	}
}
