package br.com.postech.software.architecture.techchallenge.service;

import br.com.postech.software.architecture.techchallenge.dto.ProdutoDTO;
import br.com.postech.software.architecture.techchallenge.enums.CategoriaEnum;
import br.com.postech.software.architecture.techchallenge.model.Produto;

import java.util.List;

public interface ProdutoService {

    List<ProdutoDTO> findAll(CategoriaEnum categoria);

    ProdutoDTO findById(Long id);

    Produto findProdutoById(Long id);

    ProdutoDTO save(ProdutoDTO produtoDTO);

    ProdutoDTO atualizar(ProdutoDTO produtoDTO);
    
    void deleteById(Long id);

}
