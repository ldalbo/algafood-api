package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Integer quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal precoTotal;

    private String observacao;


    @ManyToOne
    @JoinColumn(name="pedido_id",nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name="produto_id",nullable = false)
    private Produto produto;

}
