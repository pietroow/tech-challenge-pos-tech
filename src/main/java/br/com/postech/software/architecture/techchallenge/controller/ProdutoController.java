package br.com.postech.software.architecture.techchallenge.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.software.architecture.techchallenge.dto.ProdutoDTO;
import br.com.postech.software.architecture.techchallenge.service.IProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private IProdutoService produtoService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON)
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() throws Exception{
        return new ResponseEntity<>(produtoService.findAll(), HttpStatus.OK );
    }
	
	@GetMapping(path = "{idProduto}", produces=MediaType.APPLICATION_JSON)
    public ResponseEntity<ProdutoDTO> buscarProduto(@PathVariable("idProduto") Integer idProduto) throws Exception{
        return new ResponseEntity<>(produtoService.findById(idProduto), HttpStatus.OK );
    }
}
