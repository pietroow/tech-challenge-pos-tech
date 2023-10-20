package br.com.postech.software.architecture.techchallenge.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.postech.software.architecture.techchallenge.model.Cliente;
import br.com.postech.software.architecture.techchallenge.repository.jpa.IClienteJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.IClientService;

@Service
public class ClienteServiceImpl implements IClientService {

//	@Autowired
	private IClienteJpaRepository clienteJpaRepository;
	
	protected IClienteJpaRepository getPersistencia() {
		return clienteJpaRepository;
	}

	@Override
	public List<Cliente> findAll() {
		return getPersistencia().findAll();
	}

	@Override
	public Cliente findById(Integer id) {
		return getPersistencia().findById(id).orElseThrow();
	}
}
