package br.com.postech.software.architecture.techchallenge.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.connector.MercadoLivreConnector;
import br.com.postech.software.architecture.techchallenge.dto.MercadoPagoQrCodeRequestDTO;
import br.com.postech.software.architecture.techchallenge.dto.MercadoPagoQrCodeResponseDTO;
import br.com.postech.software.architecture.techchallenge.dto.PagamentoDTO;
import br.com.postech.software.architecture.techchallenge.enums.StatusPagamentoEnum;
import br.com.postech.software.architecture.techchallenge.exception.NotFoundException;
import br.com.postech.software.architecture.techchallenge.model.Pagamento;
import br.com.postech.software.architecture.techchallenge.repository.jpa.PagamentoJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.PagamentoService;

@Service
public class PagamentoServiceImpl implements PagamentoService {

	private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();

	@Autowired
	private PagamentoJpaRepository pagamentoJpaRepository;

	@Autowired
	private MercadoLivreConnector mercadoLivreConnector;

	protected PagamentoJpaRepository getPersistencia() {
		return pagamentoJpaRepository;
	}

	@Override
	public PagamentoDTO findByIdPedido(Integer idPedido) {
		Optional<Pagamento> optPagamento = pagamentoJpaRepository.findByPedidoId(idPedido);
		if(optPagamento.isPresent()){
			return MAPPER.map(optPagamento.get(), PagamentoDTO.class);
		}
		throw new NotFoundException("Pagamento não encontrado.");
	}

	@Override
	public PagamentoDTO obterStatusPagamento(Integer idPedido) {
		
		PagamentoDTO pagamentoDTO = findByIdPedido(idPedido);
		pagamentoDTO.setDescricaoStatusPagamento(StatusPagamentoEnum.get(pagamentoDTO.getStatusPagamento()).getDescricao());
		return pagamentoDTO;
	}

	@Override
	public String gerarCodigoQRPagamento(Integer idPedido) throws Exception {
		return mercadoLivreConnector.generateMercadoPagoQrCode(new MercadoPagoQrCodeRequestDTO()).getQrCode();
	}
}