package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name="nome")
    private String nome;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

}
