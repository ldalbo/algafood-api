package com.algaworks.algafood.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);

    }

    public UsuarioNaoEncontradoException(Long Id){
       super(String.format("Usuário %d não foi encontrado",Id));

    }
}
