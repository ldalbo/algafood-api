package com.algaworks.algafood.domain.model;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
public class Estado {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @NotNull
    @Column(name="nome")
    private String nome;
}

