package br.com.postech.software.architecture.techchallenge.producao.controller;

import br.com.postech.software.architecture.techchallenge.producao.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.producao.dto.ProducaoUpdateDTO;
import br.com.postech.software.architecture.techchallenge.producao.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/v1/producao")
public class ProducaoController {

    @Autowired
    private PedidoService pedidoService;

    //PRODUCAO
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<PedidoDTO>> listarTodosPedidosAtivos() throws Exception {
        return new ResponseEntity<>(pedidoService.findTodosPedidosAtivos(), HttpStatus.OK);
    }

    //PRODUCAO
    @GetMapping(path = "/{idPedido}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<PedidoDTO> buscarPedido(@PathVariable Integer idPedido) throws Exception {
        return new ResponseEntity<>(pedidoService.findById(idPedido), HttpStatus.OK);
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<ProducaoUpdateDTO> salvaPedido(@RequestBody ProducaoUpdateDTO producaoUpdateDTO) throws Exception {
        return new ResponseEntity<>(pedidoService.salvaPedido(producaoUpdateDTO), HttpStatus.OK);
    }
}