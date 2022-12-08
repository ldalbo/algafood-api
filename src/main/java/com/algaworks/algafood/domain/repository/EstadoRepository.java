package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> todos();
    Estado adicionar(Estado estado);
    Estado porId(Long Id);
    void remover(Long Id);

}
