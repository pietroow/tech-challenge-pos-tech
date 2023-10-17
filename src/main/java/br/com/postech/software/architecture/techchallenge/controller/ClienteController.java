package br.com.postech.software.architecture.techchallenge.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.software.architecture.techchallenge.model.Cliente;
import br.com.postech.software.architecture.techchallenge.service.IClientService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private IClientService clienteService;
	
    @GetMapping(produces=MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Cliente>> listarClientes(){
        return new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK );
    }
}
