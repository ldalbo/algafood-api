package com.algaworks.algafood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CidadeResumoModel {

    // COMO SÃO APENAS ATRIBUTOS DE SAIDA
    // NÃO PRECISA TER VALIDAÇÕES
    @ApiModelProperty(value = "ID da Cidade" , example = "1")
    private Long id;

    @ApiModelProperty( example = "Caxias do Sul")
    private String nome;
    private String  estado;

}
