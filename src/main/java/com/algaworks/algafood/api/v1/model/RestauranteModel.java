package com.algaworks.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class RestauranteModel {
    Long id;
    String nome;
    BigDecimal taxaFrete;
    CozinhaModel cozinha;
    Boolean ativo;
    Boolean aberto;
    EnderecoModel endereco;
    List<ProdutoModel> produto;
}
