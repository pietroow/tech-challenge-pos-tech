package br.com.postech.software.architecture.techchallenge.model;

import java.io.Serializable;
import java.util.List;

public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id; 
	private Cliente cliente;	
	private List<Produto> produtos;
	
	public Pedido() {
		super();
	}

	public Pedido(Long id, Cliente cliente, List<Produto> produtos) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.produtos = produtos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	} 
}
