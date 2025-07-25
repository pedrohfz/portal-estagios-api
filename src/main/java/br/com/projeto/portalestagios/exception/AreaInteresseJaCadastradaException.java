package br.com.projeto.portalestagios.exception;

public class AreaInteresseJaCadastradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public AreaInteresseJaCadastradaException(String nome) {
        super("A área de interesse '" + nome + "' já está cadastrada.");
    }

}
