package br.com.postech.software.architecture.techchallenge.service;

import br.com.postech.software.architecture.techchallenge.dto.PagamentoDTO;

public interface PagamentoService {

	PagamentoDTO findByIdPedido(Long idPedido);
	PagamentoDTO obterStatusPagamento(Long idPedido);
}