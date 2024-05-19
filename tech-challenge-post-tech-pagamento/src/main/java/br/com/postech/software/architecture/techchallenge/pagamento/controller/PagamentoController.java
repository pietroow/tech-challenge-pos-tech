package br.com.postech.software.architecture.techchallenge.pagamento.controller;

import javax.ws.rs.core.MediaType;

import br.com.postech.software.architecture.techchallenge.pagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;
    
    @GetMapping(path = "/{idPedido}/qrCode", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> gerarQrCode(@PathVariable Long idPedido) throws Exception {
        return new ResponseEntity<String>(pagamentoService.gerarCodigoQRPagamento(idPedido), HttpStatus.CREATED);
    }

    @PostMapping(path = "/callback/mercado-seguro/status", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> callbackPagamento(@RequestParam("topic") String topic,
            @RequestParam("id") Double mercadoPagoId) throws Exception {

        pagamentoService.validatedPaymentCallback(topic, mercadoPagoId);
        pagamentoService.updatePaymentStatus(mercadoPagoId);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}