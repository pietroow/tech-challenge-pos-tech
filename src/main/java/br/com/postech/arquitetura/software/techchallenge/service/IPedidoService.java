package br.com.postech.arquitetura.software.techchallenge.service;

import java.util.List;

import br.com.postech.arquitetura.software.techchallenge.model.Pedido;

public interface IPedidoService {

	List<Pedido> findAll();
	
	Pedido findById(Integer id);
}
