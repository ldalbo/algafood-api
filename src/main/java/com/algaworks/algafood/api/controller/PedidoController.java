package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.*;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    PedidoInputDissambler pedidoInputDissambler;

    @Autowired
    PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    EmissaoPedidoService emissaoPedido;

    @GetMapping
    public List<PedidoResumoModel>  listar(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        return  pedidoResumoModelAssembler.toCollectionModel(pedidos);
    }


    @GetMapping("/{pedidoId}")
    public PedidoModel get(@PathVariable Long pedidoId){
        Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);
        return pedidoModelAssembler.toModel(pedido);
    }

    // ALGAWORKS
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDissambler.domainToObject(pedidoInput);
            novoPedido = emissaoPedido.emitir(novoPedido);
            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }


}
