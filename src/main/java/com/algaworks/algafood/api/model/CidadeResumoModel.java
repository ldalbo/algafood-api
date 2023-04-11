package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CidadeResumoModel {

    // COMO SÃO APENAS ATRIBUTOS DE SAIDA
    // NÃO PRECISA TER VALIDAÇÕES
    private Long id;
    private String nome;
    private String  estado;

}
