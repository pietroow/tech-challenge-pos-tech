package br.com.postech.software.architecture.techchallenge.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.dto.ClienteDTO;
import br.com.postech.software.architecture.techchallenge.model.Cliente;
import br.com.postech.software.architecture.techchallenge.repository.jpa.ClienteJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.IClientService;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteServiceImpl implements IClientService {

	@Autowired
	private ClienteJpaRepository clienteJpaRepository;
	private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();

	protected ClienteJpaRepository getPersistencia() {
		return clienteJpaRepository;
	}

	@Override
	public List<ClienteDTO> listarClientesAtivos() {
		List<Cliente> clientesAtivos = getPersistencia().findByStatus('1');
		return clientesAtivos.stream()
				.map(cliente -> MAPPER.map(cliente, ClienteDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ClienteDTO findById(Integer id) {
		Optional<Cliente> cliente = getPersistencia().findByIdAndStatus(id, '1');
		if (cliente.isPresent()) {
			return MAPPER.map(cliente.get(), ClienteDTO.class);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
	}

	@Override
	public ClienteDTO save(ClienteDTO clienteDTO) {
		var cliente = MAPPER.map(clienteDTO, Cliente.class);

		cliente = getPersistencia().save(cliente);

		return MAPPER.map(cliente, ClienteDTO.class);
	}

	@Override
	public ClienteDTO atualizarCliente(Integer id, ClienteDTO clienteDTO) {
		Optional<Cliente> clienteOptional = getPersistencia().findById(id);

		if (clienteOptional.isPresent()) {
			Cliente clienteExistente = clienteOptional.get();
			clienteExistente.setNome(clienteDTO.getNome());
			clienteExistente.setEmail(clienteDTO.getEmail());
			clienteExistente.setCpf(clienteDTO.getCpf());

			clienteExistente = getPersistencia().save(clienteExistente);

			return MAPPER.map(clienteExistente, ClienteDTO.class);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
		}
	}

	@Override
	public ClienteDTO desativarCliente(Integer id) {
		Optional<Cliente> clienteOptional = getPersistencia().findById(id);

		if (clienteOptional.isPresent()) {
			Cliente cliente = clienteOptional.get();
			cliente.setStatus('0');
			cliente = getPersistencia().save(cliente);

			return MAPPER.map(cliente, ClienteDTO.class);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
		}
	}
}
