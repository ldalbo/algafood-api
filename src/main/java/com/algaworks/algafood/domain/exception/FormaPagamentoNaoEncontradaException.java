package com.algaworks.algafood.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException{

    public FormaPagamentoNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public FormaPagamentoNaoEncontradaException(Long id){
        super(String.format("Forma de pagamento %s não encontrada",id));
    }

}



