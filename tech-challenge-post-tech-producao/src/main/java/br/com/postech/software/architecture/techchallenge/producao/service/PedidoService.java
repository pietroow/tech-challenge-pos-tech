package br.com.postech.software.architecture.techchallenge.producao.service;

import br.com.postech.software.architecture.techchallenge.producao.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.producao.dto.ProducaoUpdateDTO;
import br.com.postech.software.architecture.techchallenge.producao.exception.BusinessException;

import java.util.List;

public interface PedidoService {

	List<PedidoDTO> findTodosPedidosAtivos() throws BusinessException;
	
	PedidoDTO findById(Integer id) throws BusinessException;

	ProducaoUpdateDTO salvaPedido(ProducaoUpdateDTO producaoUpdateDTO);
}
