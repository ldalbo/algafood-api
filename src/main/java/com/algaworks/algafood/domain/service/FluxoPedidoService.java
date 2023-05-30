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
    public void cancelar(Long pedidoId){
        Pedido pedido   = pedidoService.buscarOuFalhar(pedidoId);
        pedido.cancelar();
    }

    @Transactional
    public void entregar(Long pedidoId){
        Pedido pedido   = pedidoService.buscarOuFalhar(pedidoId);
        pedido.entregar();
    }


    @Transactional
    public void confirmar (Long pedidoId){
        Pedido pedido   = pedidoService.buscarOuFalhar(pedidoId);
        pedido.confirmar();
    }

}
