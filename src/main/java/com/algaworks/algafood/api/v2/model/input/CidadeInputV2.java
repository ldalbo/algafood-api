package com.algaworks.algafood.api.v2.model.input;

import com.algaworks.algafood.api.v1.model.input.EstadoIdInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CidadeInputV2 {



    @ApiModelProperty(example = "Caxias do Sul", required = true)
    @NotNull
    private String nomeCidade;


    @ApiModelProperty(example = "1", required = true)
    @Valid
    @NotNull
    private Long idEstado;

}
