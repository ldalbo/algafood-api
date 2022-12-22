package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long Id;

    private String nome;



    @Column(name="taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    // MUITOS RESTAURANTES, PODEM APONTAR PARA
    // A MESMA  COZINHA
    // NO TESTE FEITO, TEMOS 3 RESTAURANTES, APONTANDO PARA 3
    // COZINAHS
    @JsonIgnore
    /// @JsonIgnoreProperties("hibernateLazyInitializer")
    @ManyToOne (fetch = FetchType.LAZY)
    // DIGO O NOME DA COLUNA QUE DEVE SER CRIADA
    @JoinColumn(name="cozinha_id",nullable = false)
    private Cozinha cozinha;

    @JsonIgnore
    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name ="restaurante_id"),
            inverseJoinColumns = @JoinColumn (name= "forma_pagamento_id"))
    private List<FormaPagamento> formaPagamento = new ArrayList<>();


    @JsonIgnore
    @ManyToMany
    @JoinTable (name="restaurante_usuario",
            joinColumns = @JoinColumn(name="restaurante_id"),
            inverseJoinColumns = @JoinColumn(name="usuario_id")
    )
    List<Usuario> usuarios = new ArrayList<>();



}