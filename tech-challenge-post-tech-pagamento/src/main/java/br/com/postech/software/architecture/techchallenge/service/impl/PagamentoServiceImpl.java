package br.com.postech.software.architecture.techchallenge.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.connector.MercadoLivreConnector;
import br.com.postech.software.architecture.techchallenge.dto.MercadoPagoQrCodeRequestDTO;
import br.com.postech.software.architecture.techchallenge.service.PagamentoService;

@Service
public class PagamentoServiceImpl implements PagamentoService {

	private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();

	@Autowired
	private MercadoLivreConnector mercadoLivreConnector;

	@Override
	public String gerarCodigoQRPagamento(Long idPedido) throws Exception {
		return mercadoLivreConnector.generateMercadoPagoQrCode(new MercadoPagoQrCodeRequestDTO()).getQrCode();
	}
}