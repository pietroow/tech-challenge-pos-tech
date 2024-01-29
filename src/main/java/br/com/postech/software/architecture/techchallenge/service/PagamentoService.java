package br.com.postech.software.architecture.techchallenge.service;

import br.com.postech.software.architecture.techchallenge.dto.PagamentoDTO;

public interface PagamentoService {

	PagamentoDTO findByIdPedido(Integer idPedido);

	PagamentoDTO obterStatusPagamento(Integer idPedido);

	String gerarCodigoQRPagamento(Integer idPedido) throws Exception;
}