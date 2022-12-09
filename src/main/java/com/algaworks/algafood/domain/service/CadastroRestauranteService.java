package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante){
       Long cozinhaId = restaurante.getCozinha().getId();
       Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);


       if (cozinha.isEmpty()) {
           throw new EntidadeNaoEncontradaException(
            "Cozinha não encontrada " + cozinhaId);

       }
       restaurante.setCozinha(cozinha.get());
       return restauranteRepository.adicionar(restaurante);

    }
}
