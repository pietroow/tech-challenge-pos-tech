package br.com.postech.software.architecture.techchallenge.pagamento.configuration;

import org.modelmapper.AbstractConverter;

import br.com.postech.software.architecture.techchallenge.enums.StatusPedidoEnum;

public class StatusPedidoParaInteiroConverter extends AbstractConverter<StatusPedidoEnum , Integer> {

	@Override
	protected Integer convert(StatusPedidoEnum source) {
		return source == null ? null : source.getValue();
	}

}
