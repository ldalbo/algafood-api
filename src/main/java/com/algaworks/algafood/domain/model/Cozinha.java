package com.algaworks.algafood.domain.model;


import com.algaworks.algafood.core.validation.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data

public class Cozinha {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    @Column(name="nome")
    private String nome;



    @OneToMany(mappedBy = "cozinha")
    private List<Restaurante> restaurante = new ArrayList<>();



}
