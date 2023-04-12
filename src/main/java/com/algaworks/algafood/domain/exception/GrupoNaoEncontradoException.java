package com.algaworks.algafood.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public GrupoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public GrupoNaoEncontradoException(Long id){
        super(String.format("Grupo de permissão %s não encontrado",id));
    }

}



