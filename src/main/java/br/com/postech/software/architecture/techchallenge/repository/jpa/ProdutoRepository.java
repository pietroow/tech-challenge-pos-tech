package br.com.postech.software.architecture.techchallenge.repository.jpa;

import br.com.postech.software.architecture.techchallenge.enums.CategoriaEnum;
import br.com.postech.software.architecture.techchallenge.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByCategoria(CategoriaEnum categoria);
}
