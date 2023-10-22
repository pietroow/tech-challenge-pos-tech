package br.com.postech.software.architecture.techchallenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.dto.ProdutoDTO;
import br.com.postech.software.architecture.techchallenge.model.Produto;
import br.com.postech.software.architecture.techchallenge.repository.jpa.ProdutoJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.IProdutoService;

@Service
public class ProdutoServiceImpl implements IProdutoService {
	
	@Autowired
	private ProdutoJpaRepository produtoJpaRepository;
	private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();

	protected ProdutoJpaRepository getPersistencia() {
		return produtoJpaRepository;
	}

	@Override
	public List<ProdutoDTO> findAll() {
		List<Produto> produtos = getPersistencia().findAll();
		return MAPPER.map(produtos, new TypeToken<List<ProdutoDTO>>() {}.getType());
	}

	@Override
	public ProdutoDTO findById(Integer id) {
		Optional<Produto> produto = getPersistencia().findById(id);
		if(!produto.isEmpty()) {
			return MAPPER.map(produto, ProdutoDTO.class);
		}
		return null;
	}
}
