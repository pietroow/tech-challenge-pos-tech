package br.com.postech.software.architecture.techchallenge.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PedidoDTO {

	private Long numeroPedido;
    private ClienteDTO cliente;
    private String dataPedido;
    private Integer statusPedido;
    @NotEmpty
    private List<PedidoProdutoDTO> produtos;

}
