package com.algaworks.algafood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public ProdutoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId){
        this(String.format("Produto %d não encontrado no restaurante %d",produtoId,restauranteId));
    }

}



