package com.algaworks.algafood.api.v1.controller;


import com.algaworks.algafood.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/v1/pedidos/{pedidoId}")
public class FluxoPedidoController {

    @Autowired
    FluxoPedidoService fluxoPedido;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmacao(@PathVariable String codigo){
        fluxoPedido.confirmar(codigo);

    }

    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entrega(@PathVariable String codigo){
        fluxoPedido.entregar(codigo);

    }


    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancela(@PathVariable String codigo){
        fluxoPedido.cancelar(codigo);

    }



}
