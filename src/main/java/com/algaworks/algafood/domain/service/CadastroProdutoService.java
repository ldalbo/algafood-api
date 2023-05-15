package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CadastroProdutoService {
    private static final String MSG_PRODUTO_INVALIDO = "Produto %s não pertence a resaturante %s ";

    @Autowired
    ProdutoRepository produtoRepository;


    public Produto buscarOuFalhar( Long restauranteId , Long produtoId )  {

        return produtoRepository.findById(restauranteId, produtoId)
                .orElseThrow(() ->new EntidadeNaoEncontradaException(String.format(MSG_PRODUTO_INVALIDO,produtoId,restauranteId)) {
                });

        /*
        return produtoRepository.findByIdAndRestauranteId(restauranteId, produtoId)
                .orElseThrow(() ->new EntidadeNaoEncontradaException(String.format(MSG_PRODUTO_INVALIDO,produtoId,restauranteId)) {
                });
        */


    }

    @Transactional
    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }





}
