package com.algaworks.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException{
    private static final long serialVersionUID = 1L;


    public CozinhaNaoEncontradaException(Long Id){

       super(String.format("Cozinha %s não foi encontrado",Id));
        System.out.println("CozinhaNaoEncontradaException #" + Id + "#");

    }
}
