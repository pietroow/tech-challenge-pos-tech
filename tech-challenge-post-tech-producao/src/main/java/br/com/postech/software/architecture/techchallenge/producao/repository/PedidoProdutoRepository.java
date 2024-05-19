package br.com.postech.software.architecture.techchallenge.producao.repository;

import br.com.postech.software.architecture.techchallenge.producao.enums.StatusPedidoEnum;
import br.com.postech.software.architecture.techchallenge.producao.exception.PersistenceException;
import br.com.postech.software.architecture.techchallenge.producao.model.PedidoProduto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoProdutoRepository extends MongoRepository<PedidoProduto, String> {

	@Query("{numeroPedido:'?0'}")
	PedidoProduto findItemByName(String numeroPedido);

	@Query(value="{statusPedido:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
	List<PedidoProduto> findAll(String category);

	public long count();

}
