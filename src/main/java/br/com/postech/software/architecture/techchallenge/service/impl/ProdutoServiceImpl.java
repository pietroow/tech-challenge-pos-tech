package br.com.postech.software.architecture.techchallenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.postech.software.architecture.techchallenge.model.Produto;
import br.com.postech.software.architecture.techchallenge.repository.jpa.ProdutoJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.IProdutoService;
import br.com.postech.software.architecture.techchallenge.util.ValidacaoUtils;

@Service
public class ProdutoServiceImpl implements IProdutoService {
	
	@Autowired
	private ProdutoJpaRepository produtoJpaRepository;

	protected ProdutoJpaRepository getPersistencia() {
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
