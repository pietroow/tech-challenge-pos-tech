package br.com.postech.software.architecture.techchallenge.producao.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class ProducaoUpdateDTO {

    private String uuid;
    private PedidoDTO pedidoDTO;
}
