package br.com.postech.arquitetura.software.techchallenge.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.arquitetura.software.techchallenge.model.Produto;
import br.com.postech.arquitetura.software.techchallenge.service.IProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private IProdutoService produtoService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Produto>> listarleiloes(){
        return new ResponseEntity<>(produtoService.findAll(), HttpStatus.OK );
    }
}
