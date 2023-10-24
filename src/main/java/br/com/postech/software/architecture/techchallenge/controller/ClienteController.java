package br.com.postech.software.architecture.techchallenge.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.postech.software.architecture.techchallenge.dto.ClienteDTO;
import br.com.postech.software.architecture.techchallenge.service.IClientService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private IClientService clienteService;

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> clientesAtivos = clienteService.listarClientesAtivos();
        return new ResponseEntity<>(clientesAtivos, HttpStatus.OK);
    }
    
    @GetMapping(path = "{idCliente}", produces=MediaType.APPLICATION_JSON)
    public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable("idCliente") Integer idCliente ) throws Exception{
        return new ResponseEntity<>(clienteService.findById(idCliente), HttpStatus.OK );
    }

    @PostMapping(consumes=MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    @Transactional
   public ResponseEntity<ClienteDTO> salvarCliente(@RequestBody ClienteDTO clienteDTO) throws Exception{
        clienteDTO.setStatus("1".charAt(0));
        return new ResponseEntity<>(clienteService.save(clienteDTO), HttpStatus.CREATED );
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Transactional
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO updatedClienteDTO = clienteService.atualizarCliente(id, clienteDTO);

        if (updatedClienteDTO != null) {
            return new ResponseEntity<>(updatedClienteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/desativar/{id}", produces = MediaType.APPLICATION_JSON)
    @Transactional
    public ResponseEntity<ClienteDTO> desativarCliente(@PathVariable Long id) {
        ClienteDTO clienteDTO = clienteService.desativarCliente(id);

        if (clienteDTO != null) {
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
