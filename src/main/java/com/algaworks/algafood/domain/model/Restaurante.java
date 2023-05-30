package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Lazy;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nome;


    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    private Boolean ativo = Boolean.TRUE;

    private Boolean aberto = Boolean.FALSE;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @Embedded
    private Endereco endereco;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private Set<FormaPagamento> formasPagamento = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "restaurante_usuario_responsavel",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario>  responsaveis = new HashSet<>();


    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    public void ativar(){
        setAtivo(true);
    }

    public void inativar(){
        setAtivo(false);
    }


    public void abrir(){
        setAberto(true);
    }

    public void fechar(){
        setAberto(false);
    }

    public boolean removerFormaPagamento(FormaPagamento formaPagamento){
        return getFormasPagamento().remove(formaPagamento);
    }


    public boolean adicionarFormaPagamento(FormaPagamento formaPagamento){
        return getFormasPagamento().add(formaPagamento);
    }


    public boolean removerResponsavel(Usuario responsavel){
        return getResponsaveis().remove(responsavel);
    }


    public boolean adicionarResponsavel(Usuario responsavel){
        return getResponsaveis().add(responsavel);
    }

    public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento){
        return !getFormasPagamento().contains(formaPagamento);
    }


}





