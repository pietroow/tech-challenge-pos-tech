package br.com.postech.software.architecture.techchallenge.pagamento.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@JsonDeserialize
@NoArgsConstructor
@Data
public class MercadoPagoPagamentoDTO {

    private float id;
    private String status;//approved
    @JsonProperty("payment_method_id")
    private String paymentMethod;//PIX
    private String description;
    @JsonProperty("transaction_amount")
    private Double value;
    @JsonProperty("date_approved")
    private String dateApproved;
}
