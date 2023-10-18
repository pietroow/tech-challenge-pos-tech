package br.com.postech.software.architecture.techchallenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.postech.software.architecture.techchallenge.model.Pedido;
import br.com.postech.software.architecture.techchallenge.repository.jpa.IPedidoJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.IPedidoService;
import br.com.postech.software.architecture.techchallenge.util.ValidacaoUtils;

@Service
public class PedidoServiceImpl implements IPedidoService {
	
	private IPedidoJpaRepository pedidoJpaRepository;
	
	protected IPedidoJpaRepository getPersistencia() {
		return pedidoJpaRepository;
	}

	@Override
	public List<Pedido> findAll() {
		return getPersistencia().findAll();
	}

	@Override
	public Pedido findById(Integer id) {
		Optional<Pedido> pedido = getPersistencia().findById(id);
		return ValidacaoUtils.isPreenchido(pedido) ? pedido.get() : new Pedido();
	}
}
