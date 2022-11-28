package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {
    Cidade porId(Long Id);
    Cidade adicionar(Cidade cidade);
    void remover(Cidade cidade);
    List<Cidade> todos();

}
