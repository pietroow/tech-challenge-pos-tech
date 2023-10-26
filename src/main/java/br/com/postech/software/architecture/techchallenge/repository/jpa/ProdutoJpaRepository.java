package br.com.postech.software.architecture.techchallenge.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.postech.software.architecture.techchallenge.enums.CategoriaEnum;
import br.com.postech.software.architecture.techchallenge.model.Produto;

@Repository
public interface ProdutoJpaRepository extends JpaRepository<Produto, Integer> {

	List<Produto> findByCategoria(CategoriaEnum categoria);
}
