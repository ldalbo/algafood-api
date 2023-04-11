package com.algaworks.algafood.api.model.input;

import com.algaworks.algafood.domain.model.Cidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnderecoInput {



    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String numero;

    private String complemento;
    @NotBlank
    private String bairro;

    @NotNull
    private CidadeIdInput cidade;


}
