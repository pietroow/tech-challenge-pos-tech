package br.com.postech.software.architecture.techchallenge.dto;

import lombok.Data;

@Data
public class PagamentoDTO {

    private PedidoDTO pedido;
    private String statusPagamento;
}