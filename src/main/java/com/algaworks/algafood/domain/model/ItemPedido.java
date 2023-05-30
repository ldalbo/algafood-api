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

    // seta o unitario e total
    public void atribuiValoresItem(){

        setPrecoTotal(BigDecimal.ZERO);
        setPrecoUnitario(getProduto().getPreco());
        setPrecoTotal(getPrecoUnitario().multiply(new BigDecimal(getQuantidade())));
    }


    public void calcularPrecoTotal() {
        BigDecimal precoUnitario = this.getPrecoUnitario();
        Integer quantidade = this.getQuantidade();

        if (precoUnitario == null) {
            precoUnitario = BigDecimal.ZERO;
        }

        if (quantidade == null) {
            quantidade = 0;
        }

        this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
    }
}
