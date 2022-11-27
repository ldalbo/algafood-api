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

    @Column(name="taxa_entrega",updatable = false)
    private BigDecimal taxaEntrega;


}
