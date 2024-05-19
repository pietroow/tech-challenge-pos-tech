package br.com.postech.software.architecture.techchallenge.producao.service.impl;

import br.com.postech.software.architecture.techchallenge.producao.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.producao.configuration.StatusPedidoParaInteiroConverter;
import br.com.postech.software.architecture.techchallenge.producao.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.producao.dto.ProducaoUpdateDTO;
import br.com.postech.software.architecture.techchallenge.producao.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.producao.exception.BusinessException;
import br.com.postech.software.architecture.techchallenge.producao.exception.NotFoundException;
import br.com.postech.software.architecture.techchallenge.producao.model.Pedido;
import br.com.postech.software.architecture.techchallenge.producao.repository.jpa.PedidoJpaRepository;
import br.com.postech.software.architecture.techchallenge.producao.service.PedidoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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
	public PedidoDTO findById(Integer id) throws BusinessException {
		Pedido pedido = getPersistencia()
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Pedido não encontrado!"));

		return MAPPER.map(pedido, PedidoDTO.class);
	}

	@Override
	public ProducaoUpdateDTO salvaPedido(ProducaoUpdateDTO producaoUpdateDTO) {
		/*
		 * 1. Busca na base pelo id do pedido se existe registro
		 * 2. Se existir, atualizar,
		 * 3. Se não existir, criar
		 * 4. Retornar registro atualizado
		 */


		return null;
	}

}
