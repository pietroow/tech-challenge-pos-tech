package br.com.postech.software.architecture.techchallenge.model;

import br.com.postech.software.architecture.techchallenge.enums.StatusPagamentoEnum;
import br.com.postech.software.architecture.techchallenge.util.Constantes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Pagamento implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "pedido_id")
    private Long idPedido;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "data_pagamento", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataPagamento;

    @Type(value = br.com.postech.software.architecture.techchallenge.enums.AssociacaoType.class,
            parameters = {@Parameter(name = Constantes.ENUM_CLASS_NAME, value = "StatusPagamentoEnum")})
    @Column(name = "status_pedido_id")
    private StatusPagamentoEnum statusPagamento;

    public Pagamento(Long idPedido) {
        this.idPedido = idPedido;
        this.dataPagamento = LocalDateTime.now();
		this.statusPagamento = StatusPagamentoEnum.PENDENTE;
    }
}
