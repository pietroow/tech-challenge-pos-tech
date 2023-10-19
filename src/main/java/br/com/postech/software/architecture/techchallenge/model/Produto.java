package br.com.postech.software.architecture.techchallenge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nome;


	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal valor;
}
