package br.com.postech.software.architecture.techchallenge.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PagamentoDTO {

    private Long id;
    private Long idPedido;
    private LocalDateTime dataPagamento;
    private String statusPagamento;
}