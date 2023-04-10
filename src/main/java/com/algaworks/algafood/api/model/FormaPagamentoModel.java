package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class FormaPagamentoModel {

    private Long id;
    private String descricao;
}
