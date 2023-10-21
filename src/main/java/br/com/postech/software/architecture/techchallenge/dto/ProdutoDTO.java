package br.com.postech.software.architecture.techchallenge.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDTO {

	private String nome; 	
	private String categoria;
	private BigDecimal preco;
	private String descricao;
	private List<String> imagens = new ArrayList<String>();
	
	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(String nome, String categoria, BigDecimal preco, String descricao, List<String> imagens) {
		super();
		this.nome = nome;
		this.categoria = categoria;
		this.preco = preco;
		this.descricao = descricao;
		this.imagens = imagens;
	}

	public String getNome() {
		return nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<String> getImagens() {
		return imagens;
	}	
}
