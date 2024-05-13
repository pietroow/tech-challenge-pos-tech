package br.com.postech.software.architecture.techchallenge.service;

import br.com.postech.software.architecture.techchallenge.dto.PagamentoDTO;

public interface PagamentoService {

	void salvarComIdPedido(Long idPedido);

	PagamentoDTO findByIdPedido(Long idPedido);

	PagamentoDTO obterStatusPagamento(Long idPedido);

	String gerarCodigoQRPagamento(Long idPedido) throws Exception;
}