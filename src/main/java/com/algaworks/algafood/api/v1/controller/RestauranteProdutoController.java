package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.ProdutoInputDissambler;
import com.algaworks.algafood.api.v1.assembler.ProdutoModelAssembler;
import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.input.ProdutoInput;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")  // NIVEL DE CLASSE
public class RestauranteProdutoController {

    @Autowired
    CadastroRestauranteService cadastroRestaurante;

    @Autowired
    CadastroProdutoService cadastroProduto;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    ProdutoInputDissambler produtoInputDissambler;

    @GetMapping
    public List<ProdutoModel> listar(@PathVariable Long restauranteId,
                                     @RequestParam(required = false) boolean incluirInativos) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        List<Produto> produtos;
        if (incluirInativos){
            produtos = produtoRepository.findByRestaurante(restauranteId);
        }
        else{
            produtos = produtoRepository.findAtivosByRestaurante(restauranteId);
        }
        return produtoModelAssembler.toCollectionModel(produtos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoModel buscaUm(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
        return produtoModelAssembler.toModel(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adicionarProduto(@PathVariable  Long restauranteId,
                                  @RequestBody @Valid ProdutoInput produtoInput) {

        Produto produto = new Produto();
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        produto = produtoInputDissambler.domainToObject(produtoInput);
        produto.setRestaurante(restaurante);
        return produtoModelAssembler.toModel(cadastroProduto.salvar(produto));
    }


    @PutMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoModel atualizarProduto(@PathVariable Long restauranteId,
                                         @PathVariable Long produtoId,
                                         @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produtoSalvo = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
        produtoInputDissambler.copyDomainToObject(produtoInput,produtoSalvo);
        return produtoModelAssembler.toModel(cadastroProduto.salvar(produtoSalvo));
    }



}


