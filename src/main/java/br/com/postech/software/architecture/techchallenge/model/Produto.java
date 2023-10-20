package br.com.postech.software.architecture.techchallenge.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.postech.software.architecture.techchallenge.enums.CategoriaEnum;
import br.com.postech.software.architecture.techchallenge.util.Constantes;

@Entity
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nome; 	
	@Type(type = Constantes.ASSOCIACAO_TYPE, parameters = {
			@Parameter(name = Constantes.ENUM_CLASS_NAME, value = "CategoriaEnum") })
	@Column(name = "categoria_id")
	private CategoriaEnum categoria; 
	private BigDecimal preco;
	private String descricao;
	private List<String> imagens = new ArrayList<String>();
	
	@Autowired
	
	public Produto() {
		super();
	}

	public Produto(Long id, String nome, CategoriaEnum categoria, BigDecimal preco, String descricao,
			List<String> imagens) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.preco = preco;
		this.descricao = descricao;
		this.imagens = imagens;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public CategoriaEnum getCategoria() {
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
