package br.com.projeto.portalestagios.exception;

public class InscritoVagaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    public InscritoVagaException(String mensagem) {
        super(mensagem);
    }
}
