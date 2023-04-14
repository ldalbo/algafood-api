package com.algaworks.algafood.api.model.input;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioSenhaInput {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String senhaNova;

}