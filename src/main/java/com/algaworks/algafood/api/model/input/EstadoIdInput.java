package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;


public class EstadoIdInput {


    private Long id;

    @NotBlank
    private String nome;
}

