package br.com.postech.software.architecture.techchallenge.client.service;

import br.com.postech.software.architecture.techchallenge.client.dto.ClienteDTO;
import br.com.postech.software.architecture.techchallenge.client.exception.BusinessException;
import br.com.postech.software.architecture.techchallenge.client.model.Cliente;

import java.util.List;

public interface ClientService {

    List<ClienteDTO> listarClientesAtivos();

    ClienteDTO findById(Integer id);

    ClienteDTO save(ClienteDTO clienteDTO);

    ClienteDTO atualizarCliente(Integer id, ClienteDTO clienteDTO);

    ClienteDTO desativarCliente(Integer id);

    List<ClienteDTO> findAllByCpfOrNomeOrEmail(String cpf, String nome, String email) throws BusinessException;

}
