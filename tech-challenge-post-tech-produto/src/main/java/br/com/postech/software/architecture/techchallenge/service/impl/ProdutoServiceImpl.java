package br.com.postech.software.architecture.techchallenge.service.impl;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.dto.ProdutoDTO;
import br.com.postech.software.architecture.techchallenge.exception.BusinessException;
import br.com.postech.software.architecture.techchallenge.exception.NotFoundException;
import br.com.postech.software.architecture.techchallenge.model.Produto;
import br.com.postech.software.architecture.techchallenge.model.ProdutoCategoria;
import br.com.postech.software.architecture.techchallenge.repository.ProdutoCategoriaRepository;
import br.com.postech.software.architecture.techchallenge.repository.ProdutoRepository;
import br.com.postech.software.architecture.techchallenge.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();
    private final ProdutoRepository produtoRepository;
    private final ProdutoCategoriaRepository produtoCategoriaRepository;

    @Override
    public List<ProdutoDTO> findAll(Long categoriaId) {
        if (Objects.nonNull(categoriaId)) {
            Optional<ProdutoCategoria> optCategoria = produtoCategoriaRepository.findById(categoriaId);
            if(optCategoria.isPresent()){
                return MAPPER.map(produtoRepository.findByCategoria(optCategoria.get()),
                        new TypeToken<List<ProdutoDTO>>() {
                        }.getType());
            }
        }

        return MAPPER.map(produtoRepository.findAll(),
                new TypeToken<List<ProdutoDTO>>() {
                }.getType());
    }

    @Override
    public ProdutoDTO findById(Long id) {
        Produto produto = produtoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado."));

        return MAPPER.map(produto, ProdutoDTO.class);

    }

    @Override
    public Produto findProdutoById(Long id) {
        return produtoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado."));
    }

    @Override
    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        var produto = MAPPER.map(produtoDTO, Produto.class);

        setImagesToProduto(produto);
        produto = produtoRepository.save(produto);

        return MAPPER.map(produto, ProdutoDTO.class);
    }


    @Override
    public ProdutoDTO atualizar(ProdutoDTO produtoDTO) {
        var produto = MAPPER.map(produtoDTO, Produto.class);
        setImagesToProduto(produto);

        produtoRepository.findById(produtoDTO.getId()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));

        produto = produtoRepository.save(produto);

        return MAPPER.map(produto, ProdutoDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    private void setImagesToProduto(Produto produto) {
        Optional.ofNullable(produto.getImagens())
                .orElseThrow(() -> new BusinessException("É obrigatório informar pelo menos uma imgem para o produto!"))
                .stream()
                .filter(img -> Objects.isNull(img.getProduto()))
                .forEach(img -> {
                    img.setProduto(produto);
                });
    }
}