package com.algaworks.algafood.api.model;

import com.algaworks.algafood.domain.model.Estado;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CidadeModel {

    // COMO SÃO APENAS ATRIBUTOS DE SAIDA
    // NÃO PRECISA TER VALIDAÇÕES
    private Long id;
    private String nome;
    private EstadoModel estado;

}
