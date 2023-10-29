package br.com.postech.software.architecture.techchallenge.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido_produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PedidoProduto implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PedidoProdutoPK id = new PedidoProdutoPK();
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("pedidoId")
	@JoinColumn(name = "pedido_id", insertable = false, updatable = false)
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("produtoId")
	@JoinColumn(name = "produto_id", insertable = false, updatable = false)
	private Produto produto;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
}
