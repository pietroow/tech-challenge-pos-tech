package br.com.postech.software.architecture.techchallenge.service;

import java.util.List;

import br.com.postech.software.architecture.techchallenge.dto.ClienteDTO;

public interface IClientService{

	List<ClienteDTO> listarClientesAtivos();

	ClienteDTO findById(Integer id);

    ClienteDTO save(ClienteDTO clienteDTO);

	ClienteDTO atualizarCliente(Integer id, ClienteDTO clienteDTO);

	ClienteDTO desativarCliente(Integer id);
}
