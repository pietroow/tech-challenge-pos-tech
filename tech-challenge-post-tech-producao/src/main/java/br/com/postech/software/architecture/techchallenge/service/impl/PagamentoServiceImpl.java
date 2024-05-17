package br.com.postech.software.architecture.techchallenge.service.impl;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
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

	protected PagamentoJpaRepository getPersistencia() {
		return pagamentoJpaRepository;
	}

	@Override
	public PagamentoDTO findByIdPedido(Long idPedido) {
		Optional<Pagamento> optPagamento = pagamentoJpaRepository.findByIdPedido(idPedido);
		if(optPagamento.isPresent()){
			return MAPPER.map(optPagamento.get(), PagamentoDTO.class);
		}
		throw new NotFoundException("Pagamento não encontrado.");
	}

	@Override
	public PagamentoDTO obterStatusPagamento(Long idPedido) {

		PagamentoDTO pagamentoDTO = findByIdPedido(idPedido);
		pagamentoDTO.setDescricaoStatusPagamento(pagamentoDTO.getStatusPagamento().getDescricao());
		return pagamentoDTO;
	}
}