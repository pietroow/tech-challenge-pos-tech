package br.com.postech.software.architecture.techchallenge.configuration;

import br.com.postech.software.architecture.techchallenge.enums.StatusPedidoEnum;
import org.modelmapper.AbstractConverter;

public class InteiroParaStatusPedidoConverter extends AbstractConverter<Integer, StatusPedidoEnum> {

	@Override
	protected StatusPedidoEnum convert(Integer source) {
		return StatusPedidoEnum.get(source);
	}

}
