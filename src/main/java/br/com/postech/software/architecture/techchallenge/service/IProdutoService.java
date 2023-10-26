package br.com.postech.software.architecture.techchallenge.service;

import java.util.List;

import br.com.postech.software.architecture.techchallenge.dto.ProdutoDTO;
import br.com.postech.software.architecture.techchallenge.enums.CategoriaEnum;

public interface IProdutoService {
	
	List<ProdutoDTO> findAll(CategoriaEnum categoria);

    ProdutoDTO findById(Integer id);

    ProdutoDTO save(ProdutoDTO produtoDTO);

    void deleteById(Integer id);
}
