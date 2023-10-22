package br.com.postech.software.architecture.techchallenge.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "produto_images")
@Data
public class ProdutoImages implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String path;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
