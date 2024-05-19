package br.com.postech.software.architecture.techchallenge.produto.exception;

/**
 * Classe que representa as Exceptions lançadas pela aplicação
 * 
 *
 */
public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ApplicationException(String mensagem) {
        super(mensagem);
    }
	
	public ApplicationException(Throwable throwable) {
		super(throwable);
	}

    public ApplicationException(String mensagem, final Throwable throwable) {
        super(mensagem, throwable);
    }
}
