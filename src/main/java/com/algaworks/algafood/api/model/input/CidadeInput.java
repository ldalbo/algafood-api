package com.algaworks.algafood.api.model.input;

import com.algaworks.algafood.core.validation.Groups;
import com.algaworks.algafood.domain.model.Estado;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;


@Getter
@Setter
public class CidadeInput {

    private Long id;
    @NotNull

    @NotNull
    private String nome;

    @Valid
    @NotNull
    private EstadoInput estado;

}