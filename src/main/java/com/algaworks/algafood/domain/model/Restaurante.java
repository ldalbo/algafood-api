package com.algaworks.algafood.domain.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@DynamicUpdate
public class Restaurante {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column
    private String nome;

    @Column(name="taxa_entrega")
    private BigDecimal taxaFrete;

    // MUITOS RESTAURANTES, PODEM APONTAR PARA
    // UMA COZINHA
    @ManyToOne
    // DIGO O NOME DA COLUNA QUE DEVE SER CRIADA
    @JoinColumn(name="cozinha_id")
    private Cozinha cozinha;


}
