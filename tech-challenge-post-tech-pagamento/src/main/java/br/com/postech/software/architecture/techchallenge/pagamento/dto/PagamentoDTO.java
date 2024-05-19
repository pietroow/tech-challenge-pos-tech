package br.com.postech.software.architecture.techchallenge.pagamento.dto;

import java.time.LocalDateTime;

import br.com.postech.software.architecture.techchallenge.pagamento.enums.StatusPagamentoEnum;
import lombok.Data;

@Data
public class PagamentoDTO {

    private Long id;
    private Long idPedido;
    private LocalDateTime dataPagamento;
    private StatusPagamentoEnum statusPagamento;
    private String qrCode;
    private String descricaoStatusPagamento;
}