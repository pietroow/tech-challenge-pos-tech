package br.com.postech.software.architecture.techchallenge.repository.jpa;

import br.com.postech.software.architecture.techchallenge.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;

@Repository
public interface PagamentoJpaRepository extends JpaRepository<Pagamento, Integer> {

    Optional<Pagamento> findByPedidoId(Integer idPedido);
}
