package com.algaworks.algafood.api.model;

import com.algaworks.algafood.domain.model.*;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PedidoModel {


    private String codigo;
    private BigDecimal subTotal;

    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private RestauranteResumoModel restaurante;
    private UsuarioModel cliente;
    private FormaPagamentoModel formaPagamento;
    private EnderecoModel enderecoEntrega;
    private StatusPedido status;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private LocalDateTime dataEntrega;
    private List<ItemPedidoModel> itens;

}
