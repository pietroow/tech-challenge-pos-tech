package br.com.postech.software.architecture.techchallenge.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;

import br.com.postech.software.architecture.techchallenge.enums.CategoriaEnum;
import lombok.Data;

@Entity
@Table(name = "produto")
@Data
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal valor;

	@Column(nullable = false, length = 1)
	private char status;

//	@Type(type = Constantes.ASSOCIACAO_TYPE, parameters = {
//			@Parameter(name = Constantes.ENUM_CLASS_NAME, value = "CategoriaEnum") })
	@Column(name = "categoria_id")
	private CategoriaEnum categoria;
}
