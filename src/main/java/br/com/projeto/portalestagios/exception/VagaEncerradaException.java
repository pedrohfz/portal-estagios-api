package br.com.projeto.portalestagios.exception;

public class VagaEncerradaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    public VagaEncerradaException(String mensagem) {
        super(mensagem);
    }
}
