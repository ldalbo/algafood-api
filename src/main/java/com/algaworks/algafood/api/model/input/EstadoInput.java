package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;


public class EstadoInput {

    private Long id;

    @NotBlank
    private String nome;
}

