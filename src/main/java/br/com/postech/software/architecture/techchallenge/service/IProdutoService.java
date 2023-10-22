package br.com.postech.software.architecture.techchallenge.service;

import java.util.List;

import br.com.postech.software.architecture.techchallenge.dto.ProdutoDTO;

public interface IProdutoService {

	List<ProdutoDTO> findAll();
	
	ProdutoDTO findById(Integer id);
}
