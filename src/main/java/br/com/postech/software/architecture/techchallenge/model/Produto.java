package br.com.postech.software.architecture.techchallenge.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Parameter;

import br.com.postech.software.architecture.techchallenge.enums.CategoriaEnum;
import br.com.postech.software.architecture.techchallenge.util.Constantes;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "produto")
@Data
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal valor;
	
    @Column(nullable = false, length = 500)
    private String descricao;

	@Type(value = br.com.postech.software.architecture.techchallenge.enums.AssociacaoType.class, 
	        parameters = {@Parameter(name = Constantes.ENUM_CLASS_NAME, value = "CategoriaEnum")})
	@Column(name = "categoria_id")
	private CategoriaEnum categoria;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProdutoImages> imagens;
	
}
