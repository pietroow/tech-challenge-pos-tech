package br.com.postech.software.architecture.techchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import br.com.postech.software.architecture.techchallenge.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.util.Constantes;
import jakarta.persistence.*;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Column(name = "data_pedido", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime dataPedido;
	
	@Type(value = br.com.postech.software.architecture.techchallenge.enums.AssociacaoType.class, 
	        parameters = {@Parameter(name = Constantes.ENUM_CLASS_NAME, value = "StatusPedidoEnum")})
	@Column(name = "status_pedido_id")
	private StatusPedidoEnum statusPedido;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Set<PedidoProduto> produtos;

}
