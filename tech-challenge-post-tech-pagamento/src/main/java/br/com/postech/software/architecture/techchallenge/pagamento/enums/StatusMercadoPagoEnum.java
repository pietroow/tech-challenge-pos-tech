package br.com.postech.software.architecture.techchallenge.pagamento.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusMercadoPagoEnum implements APIEnum{

	PAYMENT(0,"payment"),
	CHARGEBACKS(1, "chargebacks"),
	MERCHANT_ORDER(2, "merchant_order"),
	POINT_INTEGRATION_IPN(3, "point_integration_ipn");

	private Integer value;
	private String descricao;
}