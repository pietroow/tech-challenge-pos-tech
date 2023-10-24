package br.com.postech.software.architecture.techchallenge.exception;

public class NotFoundException extends ApplicationException {
    public NotFoundException(String mensagem) {
        super(mensagem);
    }
}
