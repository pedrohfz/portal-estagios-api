package br.com.projeto.portalestagios.exception;

public class ObjetoCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    public ObjetoCadastradoException(String mensagem) {
        super(mensagem);
    }
}
