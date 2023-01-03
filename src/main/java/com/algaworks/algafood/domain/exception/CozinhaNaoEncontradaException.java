package com.algaworks.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException{
    private static final long serialVersionUID = 1L;
    public CozinhaNaoEncontradaException(String mensagem) {
        super(mensagem);

    }

    public CozinhaNaoEncontradaException(Long Id){
       super(String.format("Cozinha %d não foi encontrado",Id));

    }
}
