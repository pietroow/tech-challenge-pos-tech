package br.com.postech.software.architecture.techchallenge.service;

import java.util.List;

import br.com.postech.software.architecture.techchallenge.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.exception.BusinessException;

public interface PedidoService {

	List<PedidoDTO> findTodosPedidosAtivos() throws BusinessException;
	
	PedidoDTO findById(Integer id) throws BusinessException;
}
