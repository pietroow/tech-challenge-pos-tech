package br.com.postech.software.architecture.techchallenge.repository.jpa;

import java.util.List;

import br.com.postech.software.architecture.techchallenge.model.Cliente;

//@Repository
public interface IClienteJpaRepository {//extends JpaRepository<Cliente, Integer>

	List<Cliente> findAll();
	Cliente findById(Integer id);
}
