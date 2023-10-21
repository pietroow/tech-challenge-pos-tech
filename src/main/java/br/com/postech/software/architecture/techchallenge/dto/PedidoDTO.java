package br.com.postech.software.architecture.techchallenge.dto;

import java.util.ArrayList;
import java.util.List;

public class PedidoDTO {

    private String nomeCliente;
    private String emailCliente;
    private String cpfCliente;
    private String senhaCliente;
    private List<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
	
    public PedidoDTO() {
		super();
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public String getSenhaCliente() {
		return senhaCliente;
	}

	public List<ProdutoDTO> getProdutos() {
		return produtos;
	}
}
