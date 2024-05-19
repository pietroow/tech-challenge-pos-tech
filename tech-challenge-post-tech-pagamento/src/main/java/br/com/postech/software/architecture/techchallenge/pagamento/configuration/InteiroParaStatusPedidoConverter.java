package br.com.postech.software.architecture.techchallenge.pagamento.configuration;

import org.modelmapper.AbstractConverter;

import br.com.postech.software.architecture.techchallenge.enums.StatusPedidoEnum;

public class InteiroParaStatusPedidoConverter extends AbstractConverter<Integer, StatusPedidoEnum> {

	@Override
	protected StatusPedidoEnum convert(Integer source) {
		return StatusPedidoEnum.get(source);
	}

}
