package br.com.postech.software.architecture.techchallenge.repository.jpa;

import br.com.postech.software.architecture.techchallenge.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPedidoJpaRepository  extends JpaRepository<Pedido, Integer>{

}
