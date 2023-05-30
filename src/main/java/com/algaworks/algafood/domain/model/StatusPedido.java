package com.algaworks.algafood.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado",CRIADO),
    ENTREGUE("Entregue",CONFIRMADO),
    CANCELADO("Cancelado",CRIADO);

    private String descricacao;
    private List<StatusPedido> statusAnteriores;

    StatusPedido(String descricao, StatusPedido... statusAnteriores){
        this.descricacao = descricao;
        this.statusAnteriores = Arrays.asList(statusAnteriores);
    }

    public String getDescricacao(){
        return this.descricacao;
    }

    public boolean naoPodeAlterarStatus(StatusPedido novoStatus){
        // Exemplo se CONFIRMADO tem o correspondente como CRIADO
        return !novoStatus.statusAnteriores.contains(this);
    }

}
