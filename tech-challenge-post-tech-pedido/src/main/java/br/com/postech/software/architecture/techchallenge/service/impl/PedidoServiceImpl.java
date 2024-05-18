package br.com.postech.software.architecture.techchallenge.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.postech.software.architecture.techchallenge.connector.ClienteConnector;
import br.com.postech.software.architecture.techchallenge.connector.ProdutoConnector;
import br.com.postech.software.architecture.techchallenge.dto.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.configuration.StatusPedidoParaInteiroConverter;
import br.com.postech.software.architecture.techchallenge.configuration.InteiroParaStatusPedidoConverter;
import br.com.postech.software.architecture.techchallenge.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.exception.BusinessException;
import br.com.postech.software.architecture.techchallenge.exception.NotFoundException;
import br.com.postech.software.architecture.techchallenge.model.Pedido;
import br.com.postech.software.architecture.techchallenge.repository.jpa.PedidoJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.PedidoService;

@AllArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {
	private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();

	private final PedidoJpaRepository pedidoJpaRepository;
	private ClienteConnector clienteConnector;
	private ProdutoConnector produtoConnector;

    protected PedidoJpaRepository getPersistencia() {
		return pedidoJpaRepository;
	}

	@Override
	public List<PedidoDTO> findTodosPedidosAtivos()throws BusinessException{

		List<Pedido> pedidos = getPersistencia().findByStatusPedidoNotIn(
				Arrays.asList(StatusPedidoEnum.CONCLUIDO, StatusPedidoEnum.CANCELADO),
				Sort.by(Sort.Direction.ASC, "dataPedido")
		);

		MAPPER.typeMap(Pedido.class, PedidoDTO.class)
				.addMappings(mapperA -> mapperA
						.using(new StatusPedidoParaInteiroConverter())
						.map(Pedido::getStatusPedido, PedidoDTO::setStatusPedido))
				.addMappings(mapper -> {
					mapper.map(src -> src.getId(),PedidoDTO::setNumeroPedido);
				});

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
	@Transactional
	public PedidoDTO fazerPedidoFake(PedidoDTO pedidoDTO) throws Exception {
		//Obtem os dados do pedido
		MAPPER.typeMap(PedidoDTO.class, Pedido.class)
				.addMappings(mapperA -> mapperA
						.using(new InteiroParaStatusPedidoConverter())
						.map(PedidoDTO::getStatusPedido, Pedido::setStatusPedido));

		Pedido pedido = MAPPER.map(pedidoDTO, Pedido.class);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setStatusPedido(StatusPedidoEnum.REALIZADO);

		ValidaClienteResponseDTO validaClienteResponseDTO = clienteConnector.validaCliente(pedidoDTO.getCliente());
		if(!validaClienteResponseDTO.isValid()) {
			throw new Exception(validaClienteResponseDTO.getErrorMessage());
		}

		ValidaProdutoResponseDTO validaProdutoResponseDTO = produtoConnector.validaProdutos(
				new ValidaProdutoRequestDTO(pedidoDTO.getProdutos()
					.stream()
					.map(PedidoProdutoDTO::getProduto)
					.collect(Collectors.toList())));
		if(!validaProdutoResponseDTO.isValid()) {
			throw new Exception(validaProdutoResponseDTO.getErrorMessage());
		}

		//Salva o pedido e obtem seu numero
		pedido = getPersistencia().save(pedido);

		MAPPER.typeMap(Pedido.class, PedidoDTO.class)
				.addMappings(mapperA -> mapperA
						.using(new StatusPedidoParaInteiroConverter())
						.map(Pedido::getStatusPedido, PedidoDTO::setStatusPedido))
				.addMappings(mapper -> {
					mapper.map(src -> src.getId(),PedidoDTO::setNumeroPedido);
				});

		return pedidoDTO;
	}
}
