package br.com.postech.arquitetura.software.techchallenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.postech.arquitetura.software.techchallenge.model.Produto;
import br.com.postech.arquitetura.software.techchallenge.repository.jpa.IProdutoJpaRepository;
import br.com.postech.arquitetura.software.techchallenge.service.IProdutoService;
import br.com.postech.arquitetura.software.techchallenge.util.ValidacaoUtils;

@Service
public class ProdutoServiceImpl implements IProdutoService {
	
	@Autowired
	private IProdutoJpaRepository produtoJpaRepository;
	
	public ProdutoServiceImpl(IProdutoJpaRepository produtoJpaRepository) {
		super();
		this.produtoJpaRepository = produtoJpaRepository;
	}

	protected IProdutoJpaRepository getPersistencia() {
		return produtoJpaRepository;
	}

	@Override
	public List<Produto> findAll() {
		return getPersistencia().findAll();
	}

	@Override
	public Produto findById(Integer id) {
		Optional<Produto> produto = getPersistencia().findById(id);
		return ValidacaoUtils.isPreenchido(produto) ? produto.get() : new Produto();
	}
}
