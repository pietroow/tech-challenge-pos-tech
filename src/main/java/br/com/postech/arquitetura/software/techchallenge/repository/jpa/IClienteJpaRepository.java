package br.com.postech.arquitetura.software.techchallenge.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.postech.arquitetura.software.techchallenge.model.Cliente;

@Repository
public interface IClienteJpaRepository extends JpaRepository<Cliente, Integer>{

}
