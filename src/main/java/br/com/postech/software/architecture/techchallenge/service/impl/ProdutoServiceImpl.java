package br.com.postech.software.architecture.techchallenge.service.impl;

import br.com.postech.software.architecture.techchallenge.configuration.ModelMapperConfiguration;
import br.com.postech.software.architecture.techchallenge.dto.ProdutoDTO;
import br.com.postech.software.architecture.techchallenge.enums.CategoriaEnum;
import br.com.postech.software.architecture.techchallenge.exception.BusinessException;
import br.com.postech.software.architecture.techchallenge.exception.NotFoundException;
import br.com.postech.software.architecture.techchallenge.model.Produto;
import br.com.postech.software.architecture.techchallenge.repository.jpa.ProdutoJpaRepository;
import br.com.postech.software.architecture.techchallenge.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private static final ModelMapper MAPPER = ModelMapperConfiguration.getModelMapper();
    private final ProdutoJpaRepository produtoJpaRepository;

    @Override
    public List<ProdutoDTO> findAll(CategoriaEnum categoria) {
        if (Objects.nonNull(categoria)) {
            return MAPPER.map(produtoJpaRepository.findByCategoria(categoria),
                    new TypeToken<List<ProdutoDTO>>() {
                    }.getType());
        }

        return MAPPER.map(produtoJpaRepository.findAll(),
                new TypeToken<List<ProdutoDTO>>() {
                }.getType());
    }

    @Override
    public ProdutoDTO findById(Integer id) {
        Produto produto = produtoJpaRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado."));

        return MAPPER.map(produto, ProdutoDTO.class);

    }

    @Override
    public Produto findById(Long id) {
        return produtoJpaRepository
                .findById(id.intValue())
                .orElseThrow(() -> new NotFoundException("Produto não encontrado."));
    }

    @Override
    @Transactional
    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        var produto = MAPPER.map(produtoDTO, Produto.class);

        validateImagesProduto(produto);
        produto = produtoJpaRepository.save(produto);

        return MAPPER.map(produto, ProdutoDTO.class);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        produtoJpaRepository.deleteById(id);
    }

    private void validateImagesProduto(Produto produto) {
        Optional.ofNullable(produto.getImagens())
                .orElseThrow(() -> new BusinessException("É obrigatório informar pelo menos uma imgem para o produto!"))
                .stream()
                .filter(img -> Objects.isNull(img.getProduto()))
                .forEach(img -> {
                    img.setProduto(produto);
                });
    }
}
