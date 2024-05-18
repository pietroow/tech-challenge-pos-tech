package br.com.postech.software.architecture.techchallenge.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class MercadoPagoQrCodeResponseDTO {

	private String qrCode;

	public MercadoPagoQrCodeResponseDTO(String qrCode){
		this.qrCode = qrCode;
	}
}
