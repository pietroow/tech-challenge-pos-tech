package br.com.postech.software.architecture.techchallenge.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.dto.ClienteDTO;
import br.com.postech.software.architecture.techchallenge.model.Cliente;
import br.com.postech.software.architecture.techchallenge.repository.jpa.ClienteJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.IClientService;

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
		Optional<Cliente> cliente = getPersistencia().findById(id);
		if(!cliente.isEmpty()) {
			return MAPPER.map(cliente, ClienteDTO.class);
		}
		return null;
	}

    @Override
    public ClienteDTO save(ClienteDTO clienteDTO) {
		var cliente = MAPPER.map(clienteDTO, Cliente.class);

		cliente = getPersistencia().save(cliente);

		return MAPPER.map(cliente, ClienteDTO.class);
    }

	@Override
	public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
		// Primeiro, verifique se o cliente com o ID fornecido existe no banco de dados
		Cliente clienteExistente = getPersistencia().findById(id);

		if (clienteExistente != null) {
			// Atualize os detalhes do cliente existente com base no clienteDTO
			clienteExistente.setNome(clienteDTO.getNome());
			clienteExistente.setEmail(clienteDTO.getEmail());
			clienteExistente.setCpf(clienteDTO.getCpf());

			// Salve as atualizações
			clienteExistente = getPersistencia().save(clienteExistente);

			return MAPPER.map(clienteExistente, ClienteDTO.class);
		} else {
			// Se o cliente não existir, retorne null ou lance uma exceção
			return null;
		}
	}

	@Override
	public ClienteDTO desativarCliente(Long id) {
		Cliente cliente = getPersistencia().findById(id);

		if (cliente != null) {
			cliente.setStatus('0'); // Defina o status como 0 (inativo)
			cliente = getPersistencia().save(cliente);

			return MAPPER.map(cliente, ClienteDTO.class);
		} else {
			return null; // Cliente não encontrado
		}
	}
}
