package br.com.postech.software.architecture.techchallenge.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import br.com.postech.software.architecture.techchallenge.enums.CategoriaEnum;
import br.com.postech.software.architecture.techchallenge.util.Constantes;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Parameter;

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

	@Type(type = Constantes.ASSOCIACAO_TYPE, parameters = {
			@Parameter(name = Constantes.ENUM_CLASS_NAME, value = "CategoriaEnum") })
	@Column(name = "categoria_id")
	private CategoriaEnum categoria;
}
