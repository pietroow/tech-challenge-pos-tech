package br.com.postech.software.architecture.techchallenge.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.software.architecture.techchallenge.model.Pedido;
import br.com.postech.software.architecture.techchallenge.service.IPedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private IPedidoService pedidoService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Pedido>> listarPedidos(){
        return new ResponseEntity<>(pedidoService.findAll(), HttpStatus.OK );
    }
}
