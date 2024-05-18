package br.com.postech.software.architecture.techchallenge.service.impl;

import br.com.postech.software.architecture.techchallenge.connector.PedidoConnector;
import br.com.postech.software.architecture.techchallenge.dto.MercadoPagoPagamentoDTO;
import br.com.postech.software.architecture.techchallenge.enums.StatusMercadoPagoEnum;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.connector.MercadoLivreConnector;
import br.com.postech.software.architecture.techchallenge.dto.MercadoPagoQrCodeRequestDTO;
import br.com.postech.software.architecture.techchallenge.service.PagamentoService;

import java.util.Objects;

@Service
public class PagamentoServiceImpl implements PagamentoService {

	private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();

	@Autowired
	private MercadoLivreConnector mercadoLivreConnector;

	@Autowired
	private PedidoConnector pedidoConnector;

	@Override
	public String gerarCodigoQRPagamento(Long idPedido) throws Exception {
		return mercadoLivreConnector.generateMercadoPagoQrCode(new MercadoPagoQrCodeRequestDTO()).getQrCode();
	}

	@Override
	public void validatedPaymentCallback(String topic, Double mercadoPagoId) throws Exception {
		if(StringUtils.isEmpty(topic)
				|| !StatusMercadoPagoEnum.PAYMENT.getDescricao().equals(topic)
				|| Objects.isNull(mercadoPagoId)) {
			throw new Exception("Invalid parameters received");
		}
		return;
	}

	@Override
	@Async
	public void updatePaymentStatus(Double mercadoPagoId) throws Exception {
		MercadoPagoPagamentoDTO mercadoPagoPagamentoDTO = mercadoLivreConnector.confirmPaymentNotification(mercadoPagoId);
		pedidoConnector.atualizaStatusPagamentoEPedido(mercadoPagoPagamentoDTO);
	}
}