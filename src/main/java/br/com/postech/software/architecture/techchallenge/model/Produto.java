package br.com.postech.software.architecture.techchallenge.model;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.postech.software.architecture.techchallenge.enums.Categoria;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "produto")
@Getter
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
