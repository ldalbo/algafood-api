package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> todas();
    List<Cozinha> consultaPorNome(String nome);
    Cozinha porId(Long id);
    Cozinha adicionar(Cozinha cozinha);
    void remover(Long id);
}
