package br.com.postech.software.architecture.techchallenge.service.impl;

import java.util.List;
import java.util.Optional;

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
	public List<ClienteDTO> findAll() {
		List<Cliente> clientes = getPersistencia().findAll();
		return MAPPER.map(clientes, new TypeToken<List<ClienteDTO>>() {}.getType());
	}

	@Override
	public ClienteDTO findById(Integer id) {
		Optional<Cliente> cliente = getPersistencia().findById(id);
		if(!cliente.isEmpty()) {
			return MAPPER.map(cliente, ClienteDTO.class);
		}
		return null;
	}
}
