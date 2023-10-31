package br.com.postech.software.architecture.techchallenge.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.configuration.StatusPedidoParaInteiroConverter;
import br.com.postech.software.architecture.techchallenge.configuration.InteiroParaStatusPedidoConverter;
import br.com.postech.software.architecture.techchallenge.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.exception.BusinessException;
import br.com.postech.software.architecture.techchallenge.exception.NotFoundException;
import br.com.postech.software.architecture.techchallenge.model.Cliente;
import br.com.postech.software.architecture.techchallenge.model.Pedido;
import br.com.postech.software.architecture.techchallenge.model.Produto;
import br.com.postech.software.architecture.techchallenge.repository.jpa.PedidoJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.ClientService;
import br.com.postech.software.architecture.techchallenge.service.PedidoService;
import br.com.postech.software.architecture.techchallenge.service.ProdutoService;
import br.com.postech.software.architecture.techchallenge.util.CpfCnpjUtil;

@Service
public class PedidoServiceImpl implements PedidoService {
	private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();
	
	@Autowired
	private PedidoJpaRepository pedidoJpaRepository;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ProdutoService produtoService;
	
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

		MAPPER.typeMap(Pedido.class, PedidoDTO.class)
		.addMappings(mapperA -> mapperA
				.using(new StatusPedidoParaInteiroConverter())
					.map(Pedido::getStatusPedido, PedidoDTO::setStatusPedido))
		.addMappings(mapper -> {
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
		MAPPER.typeMap(PedidoDTO.class, Pedido.class)
			.addMappings(mapperA -> mapperA
					.using(new InteiroParaStatusPedidoConverter())
						.map(PedidoDTO::getStatusPedido, Pedido::setStatusPedido));

		Pedido pedido = MAPPER.map(pedidoDTO, Pedido.class);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setStatusPedido(StatusPedidoEnum.REALIZADO);
				
		valideCliente(pedido);	
		
		valideProduto(pedido);
		
		//Salva o pedido e obtem seu numero
		pedido = getPersistencia().save(pedido);	
		
		MAPPER.typeMap(Pedido.class, PedidoDTO.class)
		.addMappings(mapperA -> mapperA
				.using(new StatusPedidoParaInteiroConverter())
					.map(Pedido::getStatusPedido, PedidoDTO::setStatusPedido))
		.addMappings(mapper -> {
			  mapper.map(src -> src.getId(),PedidoDTO::setNumeroPedido);
		});
		
		return MAPPER.map(pedido, PedidoDTO.class);
	}

	private void valideProduto(Pedido pedido)  throws BusinessException{
		//Verifica se o está cadastrado produtos 
		Optional.ofNullable(pedido.getProdutos())
			.orElseThrow(() -> new BusinessException("É obrigatório informar algum produto!"))
			.stream()
			.filter(pedidoProduto -> Objects.nonNull(pedidoProduto.getProduto()) && 
								     Objects.nonNull(pedidoProduto.getProduto().getId()))
			.findAny()
			.orElseThrow(() -> new BusinessException("Produto não cadastrado!"));
		
		//Atribui atualiza lista de pedido_produto.
		pedido.getProdutos()
			.stream()
			.distinct()
			.forEach(pedidoProduto -> {
					pedidoProduto.setPedido(pedido);					
					Produto produto = produtoService.findProdutoById(pedidoProduto.getProduto().getId());
					pedidoProduto.setProduto(produto);
			});
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
