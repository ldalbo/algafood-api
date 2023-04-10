package com.algaworks.algafood.domain.exception;

public class CozinhaEmUsoException extends EntidadeEmUsoException{


    private static final long serialVersionUID = 1L;
    public CozinhaEmUsoException(String mensagem) {
        super(mensagem);

    }

    public CozinhaEmUsoException(Long Id){

       super(String.format("Cozinha %s Está em uso",Id));

    }
}
