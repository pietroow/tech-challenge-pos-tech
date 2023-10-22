package br.com.postech.software.architecture.techchallenge.service;

import java.util.List;

import br.com.postech.software.architecture.techchallenge.dto.PedidoDTO;

public interface IPedidoService {

	List<PedidoDTO> findAll();
	
	PedidoDTO findById(Integer id);
}
