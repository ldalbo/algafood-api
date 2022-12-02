package com.algaworks.algafood.domain.model;


import lombok.Data;


import javax.persistence.*;


@Entity
@Data
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name="nome")
    private String nome;



}
