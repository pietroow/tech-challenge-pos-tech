package br.com.postech.software.architecture.techchallenge.service;

public interface PagamentoService {

	String gerarCodigoQRPagamento(Long idPedido) throws Exception;

	void validatedPaymentCallback(String topic, Double mercadoPagoId) throws Exception;

	void updatePaymentStatus(Double mercadoPagoId) throws Exception;
}