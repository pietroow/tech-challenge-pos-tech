package br.com.postech.software.architecture.techchallenge.service.impl;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.dto.ProdutoDTO;
import br.com.postech.software.architecture.techchallenge.enums.CategoriaEnum;
import br.com.postech.software.architecture.techchallenge.exception.NotFoundException;
import br.com.postech.software.architecture.techchallenge.model.Produto;
import br.com.postech.software.architecture.techchallenge.repository.jpa.ProdutoRepository;
import br.com.postech.software.architecture.techchallenge.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProdutoServiceImpl implements ProdutoService {


    private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();
    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<ProdutoDTO> findAll(CategoriaEnum categoria) {
        if (Objects.nonNull(categoria)) {
            return MAPPER.map(produtoRepository.findByCategoria(categoria),
                    new TypeToken<List<ProdutoDTO>>() {
                    }.getType());
        }

        return MAPPER.map(produtoRepository.findAll(),
                new TypeToken<List<ProdutoDTO>>() {
                }.getType());
    }

    @Override
    public ProdutoDTO findById(Integer id) {
        Produto produto = produtoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado."));

        return MAPPER.map(produto, ProdutoDTO.class);

    }

    @Override
    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        var produto = MAPPER.map(produtoDTO, Produto.class);

        produto = produtoRepository.save(produto);

        return MAPPER.map(produto, ProdutoDTO.class);
    }


    @Override
    public void deleteById(Integer id) {
        this.produtoRepository.deleteById(id);
    }


}
