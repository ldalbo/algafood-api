package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Restaurante {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column
    private String nome;

    @Column(name="taxa_frete")
    private BigDecimal taxaFrete;

    // MUITOS RESTAURANTES, PODEM APONTAR PARA
    // UMA COZINHA

    @ManyToOne
    // DIGO O NOME DA COLUNA QUE DEVE SER CRIADA
    @JoinColumn(name="cozinha_id")
    private Cozinha cozinha;

    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
                joinColumns = @JoinColumn(name ="restaurante_id"),
                inverseJoinColumns = @JoinColumn (name= "forma_pagamento_id"))
    private List<FormaPagamento> formaPagamento = new ArrayList<>();


}
