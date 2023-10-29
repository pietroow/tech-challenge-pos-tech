package br.com.postech.software.architecture.techchallenge.service.impl;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.dto.ProdutoDTO;
import br.com.postech.software.architecture.techchallenge.enums.CategoriaEnum;
import br.com.postech.software.architecture.techchallenge.exception.NotFoundException;
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
    public List<ProdutoDTO> findAll(CategoriaEnum categoria) {
        if (Objects.nonNull(categoria)) {
            return MAPPER.map(getPersistencia().findByCategoria(categoria),
                    new TypeToken<List<ProdutoDTO>>() {
                    }.getType());
        }

        return MAPPER.map(getPersistencia().findAll(),
                new TypeToken<List<ProdutoDTO>>() {
                }.getType());
    }

    @Override
    public ProdutoDTO findById(Integer id) {
        Produto produto = getPersistencia()
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado."));

        return MAPPER.map(produto, ProdutoDTO.class);

    }
    
    @Override
    public Produto findById(Long id) {
        Produto produto = getPersistencia()
                .findById(id.intValue())
                .orElseThrow(() -> new NotFoundException("Produto não encontrado."));

        return produto;
    }

    @Override
    @Transactional
    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        var produto = MAPPER.map(produtoDTO, Produto.class);

        produto = getPersistencia().save(produto);

        return MAPPER.map(produto, ProdutoDTO.class);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
    	getPersistencia().deleteById(id);
    }
}
