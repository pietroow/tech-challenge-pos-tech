package br.com.postech.software.architecture.techchallenge.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.postech.software.architecture.techchallenge.dto.PagamentoDTO;
import br.com.postech.software.architecture.techchallenge.service.PagamentoService;

@RestController
@RequestMapping("/v1/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;
    
    @GetMapping(path = "/{idPedido}/qrCode", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> gerarQrCode(@PathVariable Long idPedido) throws Exception {
        return new ResponseEntity<String>(pagamentoService.gerarCodigoQRPagamento(idPedido), HttpStatus.CREATED);
    }

    //TODO Inserir forma de confirmação de pagamento aqui (callback do mercado pago?)
}