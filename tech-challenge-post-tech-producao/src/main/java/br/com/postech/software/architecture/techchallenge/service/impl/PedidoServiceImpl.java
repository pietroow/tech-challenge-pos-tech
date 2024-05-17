package br.com.postech.software.architecture.techchallenge.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.configuration.StatusPedidoParaInteiroConverter;
import br.com.postech.software.architecture.techchallenge.configuration.InteiroParaStatusPedidoConverter;
import br.com.postech.software.architecture.techchallenge.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.exception.BusinessException;
import br.com.postech.software.architecture.techchallenge.exception.NotFoundException;
import br.com.postech.software.architecture.techchallenge.model.Pedido;
import br.com.postech.software.architecture.techchallenge.repository.jpa.PedidoJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.PedidoService;
import br.com.postech.software.architecture.techchallenge.util.CpfCnpjUtil;

@Service
public class PedidoServiceImpl implements PedidoService {
	private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();

	private final PedidoJpaRepository pedidoJpaRepository;
	public PedidoServiceImpl(PedidoJpaRepository pedidoJpaRepository) {
		this.pedidoJpaRepository = pedidoJpaRepository;
    }

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

}
