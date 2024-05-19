package br.com.postech.software.architecture.techchallenge.pagamento.connector;

import br.com.postech.software.architecture.techchallenge.pagamento.dto.MercadoPagoPagamentoDTO;
import br.com.postech.software.architecture.techchallenge.pagamento.dto.MercadoPagoQrCodeRequestDTO;
import br.com.postech.software.architecture.techchallenge.pagamento.dto.MercadoPagoQrCodeResponseDTO;
import br.com.postech.software.architecture.techchallenge.pagamento.enums.StatusPagamentoEnum;
import org.springframework.stereotype.Component;

@Component
public class MercadoLivreConnector {

	private static final String MERCADO_PAGO_URI = "https://api.mercadopago.com";
    private static final String MERCADO_PAGO_PAGAMENTO_URI = "https://api.mercadopago.com/v1/payments/";

	public MercadoPagoQrCodeResponseDTO generateMercadoPagoQrCode(MercadoPagoQrCodeRequestDTO dto) throws Exception {
		
		try {
			//RestTemplate restTemplate = new RestTemplate();
            //ResponseEntity<MercadoPagoQrCodeResponseDTO> responseEntity = restTemplate.postForEntity(
			//	    MERCADO_PAGO_URI, dto, MercadoPagoQrCodeResponseDTO.class);
            //return responseEntity.getBody();

			return new MercadoPagoQrCodeResponseDTO("staticQrCode");
        } catch (Exception exception) {
            throw new Exception("Erro ao gerar QR Code: " + exception.getMessage());
        }
    }

    public MercadoPagoPagamentoDTO confirmPaymentNotification(Double mercadoPagoId) throws Exception {

        try {
            String uri = MERCADO_PAGO_PAGAMENTO_URI.concat(String.valueOf(mercadoPagoId));

            //RestTemplate restTemplate = new RestTemplate();
            //ResponseEntity<MercadoPagoQrCodeResponseDTO> responseEntity = restTemplate.postForEntity(
            //	    MERCADO_PAGO_PAGAMENTO_URI, dto, MercadoPagoPagamentoDTO.class);

            MercadoPagoPagamentoDTO dto = new MercadoPagoPagamentoDTO();
            dto.setId(mercadoPagoId.floatValue());
            dto.setStatus(StatusPagamentoEnum.APROVADO.getDescricao());
            return dto;

        } catch (Exception exception) {
            throw new Exception("Erro ao buscar update de pagamento - " + exception.getMessage());
        }
    }
}
