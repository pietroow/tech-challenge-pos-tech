package br.com.postech.software.architecture.techchallenge.service;

import java.util.List;

import br.com.postech.software.architecture.techchallenge.dto.ClienteDTO;

public interface IClientService{

	List<ClienteDTO> findAll();

	ClienteDTO findById(Integer id);
	
}
