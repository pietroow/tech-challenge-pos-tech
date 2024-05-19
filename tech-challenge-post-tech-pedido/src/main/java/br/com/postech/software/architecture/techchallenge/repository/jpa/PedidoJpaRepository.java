package br.com.postech.software.architecture.techchallenge.repository.jpa;

import br.com.postech.software.architecture.techchallenge.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.exception.PersistenceException;
import br.com.postech.software.architecture.techchallenge.model.Pedido;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoJpaRepository extends JpaRepository<Pedido, Integer> {

	List<Pedido> findByStatusPedidoNotIn(List<StatusPedidoEnum> statusPedidos, Sort sort) throws PersistenceException;
}
