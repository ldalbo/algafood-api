package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.*;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /*
    @GetMapping
    public List<PedidoResumoModel>  pesquisar(PedidoFilter filtro){
        List<Pedido> pedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro));
        return  pedidoResumoModelAssembler.toCollectionModel(pedidos);
    }
    */


    @GetMapping
    public Page<PedidoResumoModel>  pesquisarFiltro(PedidoFilter filtro,
                                                    @PageableDefault(size = 2) Pageable pageable){  // se não for especificada o tamanho da página o defaul é 20

        pageable = traduzirPage(pageable);
        Page<Pedido> pedidosPage =  pedidoRepository.
                findAll(PedidoSpecs.usandoFiltro(filtro),pageable);
        List<PedidoResumoModel> pedidosModel = pedidoResumoModelAssembler.
                toCollectionModel(pedidosPage.getContent());

        return new PageImpl<>(pedidosModel,pageable,pedidosPage.getTotalElements());

    }



    @GetMapping("/{codigoPedido}")
    public PedidoModel get(@PathVariable String codigoPedido){
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
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


    private Pageable traduzirPage(Pageable apiPageable){
                // Create a Map


        Map<String, String> mapeamento = new HashMap<>();

        mapeamento.put("codigo", "codigo");
        mapeamento.put("nomeCliente", "cliente.nome");
        mapeamento.put("valorTotal", "valorTotal");


        /*

        OU
        var mapeamento = Map.of("codigo", "codigo",
                                 "nomeCliente", "cliente.nome");
        */
        return PageableTranslator.translator(apiPageable,mapeamento);



    }


}
