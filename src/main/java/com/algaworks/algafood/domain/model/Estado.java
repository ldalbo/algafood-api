package com.algaworks.algafood.domain.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;



@Entity
@Data
public class Estado {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name="nome")
    private String nome;
}

