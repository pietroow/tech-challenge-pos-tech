package br.com.postech.software.architecture.techchallenge.service;

import java.util.List;

import br.com.postech.software.architecture.techchallenge.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.exception.BusinessException;

public interface IPedidoService {

	List<PedidoDTO> findTodosPedidosAtivos() throws BusinessException;
	
	PedidoDTO findById(Integer id) throws BusinessException;
	
	PedidoDTO fazerPedidoFake(PedidoDTO pedidoDTO) throws BusinessException;
}
