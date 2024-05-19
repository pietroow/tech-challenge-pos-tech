package br.com.postech.software.architecture.techchallenge.producao.repository.jpa;

import br.com.postech.software.architecture.techchallenge.producao.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.producao.exception.PersistenceException;
import br.com.postech.software.architecture.techchallenge.producao.model.Pedido;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoJpaRepository extends JpaRepository<Pedido, Integer> {

	List<Pedido> findByStatusPedidoNotIn(List<StatusPedidoEnum> statusPedidos, Sort sort) throws PersistenceException;
}
