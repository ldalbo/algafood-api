package com.algaworks.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{
    private static final long serialVersionUID = 1L;

    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);

    }

    public RestauranteNaoEncontradoException(Long Id){
       super(String.format("Restaurante %d não foi encontrado",Id));

    }
}
