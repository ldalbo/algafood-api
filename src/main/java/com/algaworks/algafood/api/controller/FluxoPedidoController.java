package com.algaworks.algafood.api.controller;


import com.algaworks.algafood.api.model.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/pedidos/{pedidoId}")
public class FluxoPedidoController {

    @Autowired
    FluxoPedidoService fluxoPedido;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmacao(@PathVariable Long pedidoId){
        fluxoPedido.confirmar(pedidoId);

    }

    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entrega(@PathVariable Long pedidoId){
        fluxoPedido.entregar(pedidoId);

    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancela(@PathVariable Long pedidoId){
        fluxoPedido.cancelar(pedidoId);

    }



}
