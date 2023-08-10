package com.algaworks.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CidadeInput {



    @ApiModelProperty(example = "Caxias do Sul", required = true)
    @NotNull
    private String nome;

    @ApiModelProperty(example = "1", required = true)
    @Valid
    @NotNull
    private EstadoIdInput estado;

}
