package br.com.postech.software.architecture.techchallenge.repository.jpa;

import br.com.postech.software.architecture.techchallenge.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProdutoJpaRepository  extends JpaRepository<Produto, Integer> {

}
