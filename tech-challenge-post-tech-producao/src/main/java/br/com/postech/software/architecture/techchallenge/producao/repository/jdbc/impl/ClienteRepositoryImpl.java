package br.com.postech.software.architecture.techchallenge.producao.repository.jdbc.impl;

import br.com.postech.software.architecture.techchallenge.producao.repository.jdbc.IClienteJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepositoryImpl implements IClienteJdbcRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	protected JdbcTemplate getPersistencia() {
		return jdbcTemplate;
	}	
	
}
