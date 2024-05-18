package br.com.postech.software.architecture.techchallenge.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private ProdutoCategoriaDTO categoria;
    @NotNull
    @Min(1)
    private BigDecimal valor;
    @NotNull
    private String descricao;
    @NotEmpty
    private List<ProdutoImagesDTO> imagens;
}
