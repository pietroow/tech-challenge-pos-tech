package br.com.postech.software.architecture.techchallenge.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.exception.BusinessException;
import br.com.postech.software.architecture.techchallenge.exception.NotFoundException;
import br.com.postech.software.architecture.techchallenge.model.Cliente;
import br.com.postech.software.architecture.techchallenge.model.Pedido;
import br.com.postech.software.architecture.techchallenge.repository.jpa.PedidoJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.IClientService;
import br.com.postech.software.architecture.techchallenge.service.IPedidoService;
import br.com.postech.software.architecture.techchallenge.util.CpfCnpjUtil;

@Service
public class PedidoServiceImpl implements IPedidoService {
	private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();
	
	@Autowired
	private PedidoJpaRepository pedidoJpaRepository;
	@Autowired
	private IClientService clientService;
	
	protected PedidoJpaRepository getPersistencia() {
		return pedidoJpaRepository;
	}

	@Override
	public List<PedidoDTO> findTodosPedidosAtivos()throws BusinessException{
		List<Pedido> pedidos = getPersistencia()
				.findByStatusPedidoNotIn(
						Arrays.asList(
								StatusPedidoEnum.CONCLUIDO, 
								StatusPedidoEnum.CANCELADO)
				);
		
		MAPPER.typeMap(Pedido.class, PedidoDTO.class).addMappings(mapper -> {
			  mapper.map(src -> src.getId(),PedidoDTO::setNumeroPedido);
		});
		return MAPPER.map(pedidos, new TypeToken<List<PedidoDTO>>() {}.getType());
	}

	@Override
	public PedidoDTO findById(Integer id) throws BusinessException{
		Pedido pedido = getPersistencia()
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Pedido não encontrado!"));
		
		return MAPPER.map(pedido, PedidoDTO.class);
	}

	@Override
	@Transactional
	public PedidoDTO fazerPedidoFake(PedidoDTO pedidoDTO) throws BusinessException {
		//Obtem os dados do pedido
		Pedido pedido = MAPPER.map(pedidoDTO, Pedido.class);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setStatusPedido(StatusPedidoEnum.REALIZADO);
		
		valideCliente(pedido);	
		
		valideProduto(pedido);
		
		//Salva o pedido e obtem seu numero
		pedido = getPersistencia().saveAndFlush(pedido);
		MAPPER.typeMap(Pedido.class, PedidoDTO.class).addMappings(mapper -> {
			  mapper.map(src -> src.getId(),PedidoDTO::setNumeroPedido);
		});
		
		return MAPPER.map(pedido, PedidoDTO.class);
	}

	private void valideProduto(Pedido pedido)  throws BusinessException{
		//Verifica se as imagens dos produtos 
		Optional.ofNullable(pedido.getProdutos())
			.orElseThrow(() -> new BusinessException("Produto não cadastrado!"))
			.stream()
			.flatMap(produto -> produto.getImagens().stream())
			.findAny()
			.filter(img -> StringUtils.isNotBlank(img.getPath()))
			.orElseThrow(() -> new BusinessException("Imagem do produto é obrigatória!"));
	}

	private void valideCliente(Pedido pedido) throws BusinessException{
		//Caso informe dados do cliente, é obrigatorio o cliente existir
		if(Objects.nonNull(pedido.getCliente())) {
			pedido.getCliente().setCpf(CpfCnpjUtil.removeMaskCPFCNPJ(pedido.getCliente().getCpf()));
			
			Cliente cliente = clientService.findByCpfOrNomeOrEmail(pedido.getCliente().getCpf(),
					pedido.getCliente().getNome(), pedido.getCliente().getEmail());
			
			if(Objects.nonNull(pedido.getCliente())) {
				throw new BusinessException("Cliente não encontrado!");
			}
			
			pedido.setCliente(cliente);
		}
	}
}
