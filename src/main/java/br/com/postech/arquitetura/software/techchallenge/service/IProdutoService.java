package br.com.postech.arquitetura.software.techchallenge.service;

import java.util.List;

import br.com.postech.arquitetura.software.techchallenge.model.Produto;


public interface IProdutoService {

	List<Produto> findAll();
	
	Produto findById(Integer id);
}
