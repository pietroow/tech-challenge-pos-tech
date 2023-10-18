package br.com.postech.software.architecture.techchallenge.service;

import java.util.List;

import br.com.postech.software.architecture.techchallenge.model.Produto;


public interface IProdutoService {

	List<Produto> findAll();
	
	Produto findById(Integer id);
}
