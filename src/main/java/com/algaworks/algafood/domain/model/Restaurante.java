package com.algaworks.algafood.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
public class Restaurante {


    @Id
    private int Id;

    @Column
    private String nome;

    @Column(name="taxa_entrega")
    private BigDecimal taxaEntrega;


}
