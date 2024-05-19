package br.com.postech.software.architecture.techchallenge.producao.model;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("PedidoProducao")
public class PedidoProducao {

    @Id
    private String uuid;
    private Long numeroPedido;
    private Cliente cliente;
    private String dataPedido;
    private String statusPedido;
    @NotEmpty
    private List<PedidoProduto> produtos;
}
