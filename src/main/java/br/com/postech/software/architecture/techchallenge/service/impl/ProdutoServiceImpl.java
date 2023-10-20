package br.com.postech.software.architecture.techchallenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.postech.software.architecture.techchallenge.model.Produto;
import br.com.postech.software.architecture.techchallenge.repository.jpa.IProdutoJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.IProdutoService;
import br.com.postech.software.architecture.techchallenge.util.ValidacaoUtils;

@Service
public class ProdutoServiceImpl implements IProdutoService {
	
	private IProdutoJpaRepository produtoJpaRepository;

	protected IProdutoJpaRepository getPersistencia() {
		return produtoJpaRepository;
	}

	@Override
	public List<Produto> findAll() {
		return null; //getPersistencia().findAll();
	}

	@Override
	public Produto findById(Integer id) {
		Optional<Produto> produto = null; //getPersistencia().findById(id);
		return ValidacaoUtils.isPreenchido(produto) ? produto.get() : new Produto();
	}
}
