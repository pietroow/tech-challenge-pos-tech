package br.com.postech.software.architecture.techchallenge.pagamento.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MercadoPagoQrCodeRequestDTO {

    //TODO validar parametros com MercadoPago
    private String userId;
    private String appId;
    private String caixaId;
    private BigDecimal value;
}
