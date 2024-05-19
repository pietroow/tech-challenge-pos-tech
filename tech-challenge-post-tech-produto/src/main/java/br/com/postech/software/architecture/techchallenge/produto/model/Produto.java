package br.com.postech.software.architecture.techchallenge.produto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Produto implements Serializable {
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

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private ProdutoCategoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProdutoImages> imagens;
}
