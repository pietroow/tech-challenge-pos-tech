package br.com.postech.software.architecture.techchallenge.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postech.software.architecture.techchallenge.dto.PagamentoDTO;
import br.com.postech.software.architecture.techchallenge.service.PagamentoService;

@RestController
@RequestMapping("/v1/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;
    
    @GetMapping(path = "/{idPedido}/status", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<PagamentoDTO> obterStatusPagamento(@PathVariable Long idPedido) throws Exception {
        return new ResponseEntity<PagamentoDTO>(pagamentoService.obterStatusPagamento(idPedido), HttpStatus.CREATED);
    }
    
    @GetMapping(path = "/{idPedido}/qrCode", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> gerarQrCode(@PathVariable Long idPedido) throws Exception {
        return new ResponseEntity<String>(pagamentoService.gerarCodigoQRPagamento(idPedido), HttpStatus.CREATED);
    }
        
    @PostMapping(path = "/{idPedido}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> gerarQrCode(@PathVariable Long idPedido) throws Exception {
        return new ResponseEntity<String>(pagamentoService.salvarComIdPedido(idPedido), HttpStatus.CREATED);
    }
}