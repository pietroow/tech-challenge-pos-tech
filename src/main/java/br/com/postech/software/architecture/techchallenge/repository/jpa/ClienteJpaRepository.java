package br.com.postech.software.architecture.techchallenge.repository.jpa;

import java.util.List;
import java.util.Optional;

import br.com.postech.software.architecture.techchallenge.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteJpaRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findAll();

    Optional<Cliente> findByIdAndStatus(Integer id, char status);

    Optional<Cliente> findById(Integer id);
    List<Cliente> findByStatus(char c);
}
