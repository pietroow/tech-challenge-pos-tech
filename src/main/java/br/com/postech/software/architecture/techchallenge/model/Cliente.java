package br.com.postech.software.architecture.techchallenge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;

    public Cliente() {
        super();
    }

    public Cliente(Long id, String nome, String email, String cpf, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.senha = senha;
	}

	public Long getId() {
        return id;
    }

    public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }
}
