package br.com.postech.software.architecture.techchallenge.service;

import java.util.List;
import java.util.Optional;

import br.com.postech.software.architecture.techchallenge.model.Cliente;

public interface IClientService{

	List<Cliente> findAll();
	
	Optional<Cliente> findById(Integer id);
	
}
