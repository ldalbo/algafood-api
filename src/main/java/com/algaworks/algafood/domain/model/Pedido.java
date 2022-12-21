package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long Id;

    @Column(name="subtotal",nullable = false)
    private BigDecimal subTotal;

    @Column(name="taxa_frete",nullable = false)
    private BigDecimal taxaFrete;

    @Column(name="valor_total",nullable = false)
    private BigDecimal valorTotal;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="restaurante_id",nullable = false)
    private Restaurante restaurante;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_cliente_id",nullable = false)
    private Usuario usuarioCliente;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="forma_pagamento_id",nullable = false)
    private FormaPagamento formaPagamento;

    @JsonIgnore
    @Embedded
    private Endereco endereco;

    private StatusPedido status;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCriacao;

    @Column( columnDefinition = "datetime")
    private LocalDateTime dataConfirmacao;


    @Column( columnDefinition = "datetime")
    private LocalDateTime dataEntrega;


}
