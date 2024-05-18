package br.com.postech.software.architecture.techchallenge.client.exception;

public class ErrorDetails {

	private int httpStatus;
	private String mensagem;
	
	public ErrorDetails() {
		super();
	}

	public ErrorDetails(int httpStatus, String mensagem) {
		super();
		this.httpStatus = httpStatus;
		this.mensagem = mensagem;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public String getMensagem() {
		return mensagem;
	}
}
