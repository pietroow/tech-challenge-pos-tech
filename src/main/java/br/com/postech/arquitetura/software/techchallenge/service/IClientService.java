package br.com.postech.arquitetura.software.techchallenge.service;

import java.util.List;

import br.com.postech.arquitetura.software.techchallenge.model.Cliente;

public interface IClientService{

	List<Cliente> findAll();
	
	Cliente findById(Integer id);
	
}
