package br.com.postech.software.architecture.techchallenge.model;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.postech.software.architecture.techchallenge.enums.Categoria;

public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome; 
	private Categoria categoria; 
	private BigDecimal valor;
	
	public Produto() {
		super();
	}

	public Produto(Long id, String nome, Categoria categoria, BigDecimal valor) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public BigDecimal getValor() {
		return valor;
	}
}
