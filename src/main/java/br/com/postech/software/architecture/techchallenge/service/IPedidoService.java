package br.com.postech.software.architecture.techchallenge.service;

import java.util.List;

import br.com.postech.software.architecture.techchallenge.model.Pedido;

public interface IPedidoService {

	List<Pedido> findAll();
	
	Pedido findById(Integer id);
}
