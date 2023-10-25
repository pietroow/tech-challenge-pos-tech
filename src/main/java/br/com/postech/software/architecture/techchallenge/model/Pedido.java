package br.com.postech.software.architecture.techchallenge.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import br.com.postech.software.architecture.techchallenge.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.util.Constantes;
import jakarta.persistence.*;

@Entity
@Table(name = "pedido")
@Data
public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToMany
	@JoinTable(name = "pedido_produto",
			joinColumns = @JoinColumn(name = "pedido_id"),
			inverseJoinColumns = @JoinColumn(name = "produto_id"))
	private List<Produto> produtos;

	@Column(name = "data_pedido", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime dataPedido;
	
	@Type(value = br.com.postech.software.architecture.techchallenge.enums.AssociacaoType.class, 
	        parameters = {@Parameter(name = Constantes.ENUM_CLASS_NAME, value = "StatusPedidoEnum")})
	@Column(name = "status_pedido_id")
	private StatusPedidoEnum statusPedido;
}
