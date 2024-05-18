package br.com.postech.software.architecture.techchallenge.service;

import br.com.postech.software.architecture.techchallenge.dto.PagamentoDTO;

public interface PagamentoService {

	String gerarCodigoQRPagamento(Long idPedido) throws Exception;
}