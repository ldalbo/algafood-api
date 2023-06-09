package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;

import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class FluxoPedidoService {

    @Autowired
    EmissaoPedidoService pedidoService;

    @Transactional
    public void cancelar(String codigo){
        Pedido pedido   = pedidoService.buscarOuFalhar(codigo);
        pedido.cancelar();
    }

    @Transactional
    public void entregar(String codigo){
        Pedido pedido   = pedidoService.buscarOuFalhar(codigo);
        pedido.entregar();
    }


    @Transactional
    public void confirmar (String codigo){
        Pedido pedido   = pedidoService.buscarOuFalhar(codigo);
        pedido.confirmar();
    }

}
