package br.com.postech.arquitetura.software.techchallenge.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.postech.arquitetura.software.techchallenge.model.Pedido;

@Repository
public interface IPedidoJpaRepository extends JpaRepository<Pedido, Integer> {

}
