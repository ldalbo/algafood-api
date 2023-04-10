package com.algaworks.algafood.domain.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="descricao")
    private String descricao;

}
