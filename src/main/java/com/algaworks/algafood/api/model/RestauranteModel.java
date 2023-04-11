package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteModel {
    Long id;
    String nome;
    BigDecimal taxaFrete;
    CozinhaModel cozinha;
    Boolean ativo;
    EnderecoModel endereco;

}
