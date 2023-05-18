package com.algaworks.algafood.domain.exception;

public class PermissaoEmUsoException extends NegocioException{

    public PermissaoEmUsoException(String mensagem){
        super(mensagem);
    }


    public PermissaoEmUsoException(Long id){
        super(String.format("Permissao %s está em uso",id));

    }
}
