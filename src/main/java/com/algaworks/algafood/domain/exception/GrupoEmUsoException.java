package com.algaworks.algafood.domain.exception;

public class GrupoEmUsoException extends NegocioException{

    public GrupoEmUsoException(String mensagem){
        super(mensagem);

    }

    public GrupoEmUsoException(Long id){
        super(String.format("Grupo %s está em uso",id));

    }
}
