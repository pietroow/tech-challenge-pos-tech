package br.com.postech.software.architecture.techchallenge.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PedidoProdutoPK implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name = "pedido_id", insertable = false, updatable = false)
	private Long pedidoId;
	
	@Column(name = "produto_id", insertable = false, updatable = false)
	private Long produtoId;

}
