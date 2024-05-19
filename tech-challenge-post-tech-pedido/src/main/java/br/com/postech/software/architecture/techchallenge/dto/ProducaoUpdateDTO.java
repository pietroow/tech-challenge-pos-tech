
package br.com.postech.software.architecture.techchallenge.dto;

import br.com.postech.software.architecture.techchallenge.model.Pedido;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class ProducaoUpdateDTO {

	private String uuid;
    private PedidoDTO pedidoDTO;

    public ProducaoUpdateDTO(PedidoDTO pedidoDTO){
        this.pedidoDTO = pedidoDTO;
    }
}
