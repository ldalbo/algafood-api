package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CadastroProdutoService {


    @Autowired
    ProdutoRepository produtoRepository;

    public Produto buscarOuFalhar(Long id){
       return produtoRepository.findById(id)
               .orElseThrow(() ->new CidadeNaoEncontradaException(id));

    }
}
