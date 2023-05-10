package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.assembler.ProdutoInputDissambler;
import com.algaworks.algafood.api.assembler.ProdutoModelAssembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")  // NIVEL DE CLASSE
public class RestauranteProdutoController {

    @Autowired
    CadastroRestauranteService cadastroRestaurante;

    @Autowired
    CadastroProdutoService cadastroProduto;

    @Autowired
    ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    ProdutoInputDissambler produtoInputDissambler;

    @GetMapping
    public List<ProdutoModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        List<Produto> produtos = restaurante.getProdutos();
        return produtoModelAssembler.toCollectionModel(produtos);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adicionarProduto(@PathVariable  Long restauranteId,
                                  @RequestBody @Valid ProdutoInput produtoInput) {

        Produto produto = new Produto();
        produto = produtoInputDissambler.domainToObject(produtoInput);
        return produtoModelAssembler.toModel(cadastroRestaurante.adicionarProduto(restauranteId,produto));
    }


    @PutMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoModel atualizarProduto(@PathVariable Long restauranteId,
                                         @PathVariable Long produtoId,
                                         @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restauranteSalvo = cadastroRestaurante.buscarOuFalhar(restauranteId);
        Produto produtoSalvo = cadastroProduto.buscarOuFalhar(produtoId);
        produtoInputDissambler.copyDomainToObject(produtoInput,produtoSalvo);
        System.out.println(produtoSalvo.getNome() + " # restauranteId " +  produtoSalvo.getRestaurante().getId());
        return produtoModelAssembler.toModel(cadastroRestaurante.atualizarProdutoRestaurante(produtoSalvo));
    }



    /*
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GrupoModel atualizar(@RequestBody @Valid GrupoInput grupoInput, @PathVariable("id") Long id){
        Grupo grupoSalvo = cadastroGrupo.buscarOuFalhar(id);
        grupoInputDissambler.copyDomainToObject(grupoInput,grupoSalvo);
        return grupoModelAssembler.toModel(grupoSalvo);
    }
    */

}


