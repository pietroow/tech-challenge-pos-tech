package br.com.postech.software.architecture.techchallenge.connector;

import br.com.postech.software.architecture.techchallenge.dto.MercadoPagoPagamentoDTO;
import br.com.postech.software.architecture.techchallenge.dto.MercadoPagoQrCodeRequestDTO;
import br.com.postech.software.architecture.techchallenge.dto.MercadoPagoQrCodeResponseDTO;
import br.com.postech.software.architecture.techchallenge.enums.StatusPagamentoEnum;
import org.springframework.stereotype.Component;

@Component
public class PedidoConnector {

	private static final String MICROSSERVICO_PEDIDO_URI = "https://tech-challenge.pedido.com";
	private static final String UPDATE_PEDIDO_ENDPOINT = "/v1/pedidos/update/pagamento";
	public void atualizaStatusPagamentoEPedido(MercadoPagoPagamentoDTO dto) throws Exception {
		try {
			//RestTemplate restTemplate = new RestTemplate();
			//ResponseEntity<HttpStatus> responseEntity = restTemplate.postForEntity(
			//	    UPDATE_PEDIDO_URI, dto, MercadoPagoPagamentoDTO.class);

		} catch (Exception exception) {
			throw new Exception("Erro ao gerar QR Code: " + exception.getMessage());
		}
	}
}
