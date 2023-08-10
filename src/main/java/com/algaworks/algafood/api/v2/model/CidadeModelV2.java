package com.algaworks.algafood.api.v2.model;

import com.algaworks.algafood.api.v1.model.EstadoModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

;


@Getter
@Setter

// Aqui apenas foi criada pensando em forçar uma V2
// para testar o conceito

public class CidadeModelV2 {


    @ApiModelProperty(value= "ID da Cidade",example = "1")
    private Long cidadeId;

    @ApiModelProperty(example = "Caxias do Sul")
    private String nomeCidade;

    private Long idEstado;

    private String nomeEstado;

  //  private EstadoModel estado;

}
