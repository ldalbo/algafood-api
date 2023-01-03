package com.algaworks.algafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException{
    private static final long serialVersionUID = 1L;
    public CidadeNaoEncontradaException(String mensagem) {
        super(mensagem);

    }

    public CidadeNaoEncontradaException(Long Id){
       super(String.format("Cidade %d não foi encontrado",Id));

    }
}
