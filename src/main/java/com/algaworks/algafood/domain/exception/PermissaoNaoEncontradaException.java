package com.algaworks.algafood.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException{
    private static final long serialVersionUID = 1L;


    public PermissaoNaoEncontradaException(Long Id){

       super(String.format("Permissão %s não foi encontrado",Id));


    }
}
