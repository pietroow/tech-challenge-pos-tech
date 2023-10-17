package br.com.postech.arquitetura.software.techchallenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.postech.arquitetura.software.techchallenge.model.Cliente;
import br.com.postech.arquitetura.software.techchallenge.repository.jpa.IClienteJpaRepository;
import br.com.postech.arquitetura.software.techchallenge.service.IClientService;

@Service
public class ClienteServiceImpl implements IClientService {

	@Autowired
	private IClienteJpaRepository clienteJpaRepository;

	public ClienteServiceImpl(IClienteJpaRepository clienteJpaRepository) {
		super();
		this.clienteJpaRepository = clienteJpaRepository;
	}
	
	protected IClienteJpaRepository getRepository() {
		return clienteJpaRepository;
	}

	public List<Cliente> findAll() {
		return getRepository().findAll();
	}

	public Optional<Cliente> findById(Integer id) {
		return getRepository().findById(id);
	}

}
