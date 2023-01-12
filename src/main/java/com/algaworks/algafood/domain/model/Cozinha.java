package com.algaworks.algafood.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data

public class Cozinha {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(name="nome")
    private String nome;


    @JsonIgnore
    @OneToMany(mappedBy = "cozinha")
    private List<Restaurante> restaurante = new ArrayList<>();



}
