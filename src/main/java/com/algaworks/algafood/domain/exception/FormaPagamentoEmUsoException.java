package com.algaworks.algafood.domain.exception;

public class FormaPagamentoEmUsoException extends NegocioException{

    public FormaPagamentoEmUsoException(String mensagem){
        super(mensagem);

    }


    public FormaPagamentoEmUsoException(Long id){
        super(String.format("Forma de pagamento %s está em uso",id));

    }
}
