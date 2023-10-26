package br.com.postech.software.architecture.techchallenge.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.exception.BusinessException;
import br.com.postech.software.architecture.techchallenge.exception.NotFoundException;
import br.com.postech.software.architecture.techchallenge.model.Pedido;
import br.com.postech.software.architecture.techchallenge.repository.jpa.PedidoJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.IPedidoService;

@Service
public class PedidoServiceImpl implements IPedidoService {
	
	@Autowired
	private PedidoJpaRepository pedidoJpaRepository;
	private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();
	
	protected PedidoJpaRepository getPersistencia() {
		return pedidoJpaRepository;
	}

	@Override
	public List<PedidoDTO> findTodosPedidosAtivos()throws BusinessException{
		List<Pedido> pedidos = getPersistencia()
				.findByStatusPedidoNotIn(
						Arrays.asList(
								StatusPedidoEnum.CONCLUIDO, 
								StatusPedidoEnum.CANCELADO)
				);
		
		return MAPPER.map(pedidos, new TypeToken<List<PedidoDTO>>() {}.getType());
	}

	@Override
	public PedidoDTO findById(Integer id) throws BusinessException{
		Pedido pedido = getPersistencia()
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Pedido não encontrado!"));
		
		return MAPPER.map(pedido, PedidoDTO.class);
	}

	@Override
	public PedidoDTO fazerPedidoFake(PedidoDTO pedidoDTO) throws BusinessException {		
		Pedido pedido = MAPPER.map(pedidoDTO, Pedido.class);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setStatusPedido(StatusPedidoEnum.REALIZADO);
		
		if(Objects.nonNull(pedidoDTO.getCpfCliente())) {
			
		}
		
		return MAPPER.map(getPersistencia().save(pedido), PedidoDTO.class);
	}
}
