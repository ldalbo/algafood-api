package com.algaworks.algafood.api.model;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;

import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    EmissaoPedidoService pedidoService;

    @Transactional
    public void confirmar(Long pedidoId){
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
        if (!pedido.getStatus().equals(StatusPedido.CRIADO)){
           throw new NegocioException(String.format("O pedido %d não pode mudar do status %s para o status %s" ,
                   pedidoId,
                   pedido.getStatus().getDescricacao(),
                   StatusPedido.CONFIRMADO.getDescricacao()));
        }
        // SE PASSOU, VAMOS SETAR
        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());

    }

    @Transactional
    public void entregar(Long pedidoId){
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
        if (!pedido.getStatus().equals(StatusPedido.CONFIRMADO)){
            throw new NegocioException(String.format("O pedido %d não pode mudar do status %s para o status %s" ,
                    pedidoId,
                    pedido.getStatus().getDescricacao(),
                    StatusPedido.ENTREGUE.getDescricacao()));
        }
        // SE PASSOU, VAMOS SETAR
        pedido.setStatus(StatusPedido.ENTREGUE);
        pedido.setDataEntrega(OffsetDateTime.now());

    }

    @Transactional
    public void cancelar(Long pedidoId){
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
        if (!pedido.getStatus().equals(StatusPedido.CRIADO)){
            throw new NegocioException(String.format("O pedido %d não pode mudar do status %s para o status %s" ,
                    pedidoId,
                    pedido.getStatus().getDescricacao(),
                    StatusPedido.CANCELADO.getDescricacao()));
        }
        // SE PASSOU, VAMOS SETAR
        pedido.setStatus(StatusPedido.CANCELADO);


    }
}
