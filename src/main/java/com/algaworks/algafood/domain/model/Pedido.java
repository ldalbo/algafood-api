package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name="subtotal",nullable = false)
    private BigDecimal subTotal;

    @Column(name="taxa_frete",nullable = false)
    private BigDecimal taxaFrete;

    @Column(name="valor_total",nullable = false)
    private BigDecimal valorTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurante_id",nullable = false)
    private Restaurante restaurante;


    @ManyToOne
    @JoinColumn(name="usuario_cliente_id",nullable = false)
    private Usuario cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="forma_pagamento_id",nullable = false)
    private FormaPagamento formaPagamento;


    @Embedded
    private Endereco enderecoEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;


    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCriacao;

    @Column( columnDefinition = "datetime")
    private OffsetDateTime dataConfirmacao;


    @Column( columnDefinition = "datetime")
    private OffsetDateTime dataEntrega;

    @OneToMany(mappedBy = "pedido", cascade =  CascadeType.ALL)
    // @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();




    public void calcularValorTotal() {
        getItens().forEach(ItemPedido::calcularPrecoTotal);

        this.subTotal = getItens().stream()
                .map(item -> item.getPrecoTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.subTotal.add(this.taxaFrete);
    }


}
