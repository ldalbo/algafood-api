package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {


    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="nome")
    private String nome;

    // SE NÃO FAÇO VOLTAR, O BEAN FICA NULO, ENTÃO DEVEMOS CUIDAR
    // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

}
