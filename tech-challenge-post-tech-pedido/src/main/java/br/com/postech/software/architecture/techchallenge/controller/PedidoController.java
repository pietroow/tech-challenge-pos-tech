package br.com.postech.software.architecture.techchallenge.controller;

import br.com.postech.software.architecture.techchallenge.dto.PedidoDTO;
import br.com.postech.software.architecture.techchallenge.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
//    @Autowired
//    private PagamentoService pagamentoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<PedidoDTO>> listarTodosPedidosAtivos() throws Exception {
        return new ResponseEntity<>(pedidoService.findTodosPedidosAtivos(), HttpStatus.OK);
    }

    @GetMapping(path = "/{idPedido}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<PedidoDTO> buscarPedido(@PathVariable Integer idPedido) throws Exception {
        return new ResponseEntity<>(pedidoService.findById(idPedido), HttpStatus.OK);
    }

    @PostMapping(path = "/checkout", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Long> fazerCheckoutFake(@RequestBody PedidoDTO pedidoDTO) throws Exception {
        Long idPedido = pedidoService.fazerPedidoFake(pedidoDTO);
        //TODO Chamar microserviço de pagamento aqui
        //pagamentoService.salvarComIdPedido(idPedido);
        return new ResponseEntity<Long>(idPedido, HttpStatus.CREATED);
    }
}
