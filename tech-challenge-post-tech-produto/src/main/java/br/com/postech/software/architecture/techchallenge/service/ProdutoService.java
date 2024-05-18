package br.com.postech.software.architecture.techchallenge.service;

import br.com.postech.software.architecture.techchallenge.dto.ProdutoDTO;
import br.com.postech.software.architecture.techchallenge.dto.ValidaProdutoRequestDTO;
import br.com.postech.software.architecture.techchallenge.dto.ValidaProdutoResponseDTO;
import br.com.postech.software.architecture.techchallenge.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    List<ProdutoDTO> findAll(Long categoriaId);

    ProdutoDTO getDTOById(Long id);

    Produto findById(Long id);

    Optional<Produto> findOptionalById(Long id);

    ProdutoDTO save(ProdutoDTO produtoDTO);

    ProdutoDTO atualizar(ProdutoDTO produtoDTO);

    void deleteById(Long id);

    ValidaProdutoResponseDTO validateProduto(ValidaProdutoRequestDTO validaProdutoRequestDTO);
}
