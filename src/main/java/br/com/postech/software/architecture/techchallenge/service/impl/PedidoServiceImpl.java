package br.com.postech.software.architecture.techchallenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.dto.PedidoDTO;
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
	public List<PedidoDTO> findAll() {
		List<Pedido> pedidos = getPersistencia().findAll();
		return MAPPER.map(pedidos, new TypeToken<List<PedidoDTO>>() {}.getType());
	}

	@Override
	public PedidoDTO findById(Integer id) {
		Optional<Pedido> pedido = getPersistencia().findById(id);
		if(!pedido.isEmpty()) {
			return MAPPER.map(pedido, PedidoDTO.class);
		}
		return null;
	}
}
