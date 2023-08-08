package com.algaworks.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EstadoIdInput {

    @NotNull
    @ApiModelProperty(example = "1", value="Id do Estado")
    private Long id;

}

