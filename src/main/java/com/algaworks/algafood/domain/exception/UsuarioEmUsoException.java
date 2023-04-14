package com.algaworks.algafood.domain.exception;

public class UsuarioEmUsoException extends NegocioException{

    public UsuarioEmUsoException(String mensagem){
        super(mensagem);
    }


    public UsuarioEmUsoException(Long id){
        super(String.format("Forma de pagamento %s está em uso",id));

    }
}
