package br.com.postech.software.architecture.techchallenge.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.dto.PagamentoDTO;
import br.com.postech.software.architecture.techchallenge.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.enums.StatusPagamentoEnum;
import br.com.postech.software.architecture.techchallenge.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.repository.jpa.PedidoJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.PagamentoService;
import br.com.postech.software.architecture.techchallenge.service.PedidoService;

@Service
public class PagamentoServiceImpl implements PagamentoService {
	private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();

	@Autowired
	private PedidoJpaRepository pedidoJpaRepository;
	@Autowired
	private PedidoService pedidoService;

	protected PedidoJpaRepository getPersistencia() {
		return pedidoJpaRepository;
	}

	@Override
	public PagamentoDTO obterStatusPagamento(Integer idPedido) {
		
		PedidoDTO pedido = pedidoService.findById(idPedido);
		PagamentoDTO pagamentoDTO = new PagamentoDTO();
		
		if(pedido.getStatusPedido() >= StatusPedidoEnum.CONFIRMADO.getValue()){
			pagamentoDTO.setPedido(pedido);
			pagamentoDTO.setStatusPagamento(StatusPagamentoEnum.APROVADO.getDescricao());
		}
		else if(pedido.getStatusPedido() == StatusPedidoEnum.PENDENTE.getValue()
			|| pedido.getStatusPedido() == StatusPedidoEnum.REALIZADO.getValue()){
				pagamentoDTO.setPedido(pedido);
				pagamentoDTO.setStatusPagamento(StatusPagamentoEnum.PENDENTE.getDescricao());
		}
		else{
			pagamentoDTO.setPedido(pedido);
			pagamentoDTO.setStatusPagamento(StatusPagamentoEnum.DESCONHECIDO.getDescricao());
		}
		
		return pagamentoDTO;
	}
}