package com.algaworks.algafood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInput {

    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @NotNull
    private Boolean ativo;

}
