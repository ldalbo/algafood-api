package com.algaworks.algafood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{
    private static final long serialVersionUID = 1L;



    public PedidoNaoEncontradoException(String codigo){
       super(String.format("Pedido %d não foi encontrado",codigo));

    }
}
