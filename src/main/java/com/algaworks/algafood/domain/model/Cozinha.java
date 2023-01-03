package com.algaworks.algafood.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name="nome")
    private String nome;

    /*
    @JsonIgnore
    @OneToMany(mappedBy = "cozinha")
    private List<Restaurante> restaurante = new ArrayList<>();
    */


}
