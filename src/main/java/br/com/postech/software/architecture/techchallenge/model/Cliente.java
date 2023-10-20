package br.com.postech.software.architecture.techchallenge.model;

import java.io.Serializable;

public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cpf;
	private String senha;
	
	public Cliente() {
		super();
	}

	public Cliente(Long id, String cpf, String senha) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public String getSenha() {
		return senha;
	}
}
