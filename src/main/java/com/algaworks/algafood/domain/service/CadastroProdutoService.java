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

import java.util.List;


@Service
public class CadastroProdutoService {


    @Autowired
    ProdutoRepository produtoRepository;



    public Produto buscarOuFalhar(Long id){
       return produtoRepository.findById(id)
               .orElseThrow(() ->new EntidadeNaoEncontradaException("Produto " + id + "não encontrado") {
               });

    }



}
