package com.algaworks.algafood.domain.service;



import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PedidoService {
    private static final String MSG_PRODUTO_INVALIDO = "Produto %s não pertence a resaturante %s ";

    @Autowired
    PedidoRepository pedidoRepository;


    public Pedido buscarOuFalhar(Long pedidoId)  {

        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() ->new PedidoNaoEncontradoException(pedidoId) {
                });
    }




}
