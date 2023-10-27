package br.com.postech.software.architecture.techchallenge.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PedidoDTO {

	private Long numeroPedido;
    private String nomeCliente;
    private String emailCliente;
    private String cpfCliente;
    private String senhaCliente;
    private String dataPedido;
    @NotEmpty
    private List<ProdutoDTO> produtos;

}
