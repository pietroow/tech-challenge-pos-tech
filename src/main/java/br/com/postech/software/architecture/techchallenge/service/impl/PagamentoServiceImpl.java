package br.com.postech.software.architecture.techchallenge.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.dto.PagamentoDTO;
import br.com.postech.software.architecture.techchallenge.exception.NotFoundException;
import br.com.postech.software.architecture.techchallenge.model.Pagamento;
import br.com.postech.software.architecture.techchallenge.repository.jpa.PagamentoJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.PagamentoService;

@Service
public class PagamentoServiceImpl implements PagamentoService {

	private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();

	@Autowired
	private PagamentoJpaRepository pagamentoJpaRepository;

	protected PagamentoJpaRepository getPersistencia() {
		return pagamentoJpaRepository;
	}

	public Pagamento findByIdPedido(Integer idPedido){
		Optional<Pagamento> optPagamento = pagamentoJpaRepository.findByPedidoId(idPedido);
		if(optPagamento.isPresent()){
			return optPagamento.get();
		}
		throw new NotFoundException("Pagamento não encontrado.");
	}

	@Override
	public PagamentoDTO obterStatusPagamento(Integer idPedido) {
		
		Pagamento pagamento = findByIdPedido(idPedido);
		PagamentoDTO pagamentoDTO = MAPPER.map(pagamento, PagamentoDTO.class);
		pagamentoDTO.setStatusPagamento(pagamento.getStatusPagamento().getDescricao());
		return pagamentoDTO;
	}
}