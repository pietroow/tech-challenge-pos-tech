package br.com.postech.software.architecture.techchallenge.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.postech.software.architecture.techchallenge.model.Cliente;

@Repository
public interface IClienteJpaRepository extends JpaRepository<Cliente, Integer>{

}
