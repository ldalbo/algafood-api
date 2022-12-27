package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroRestauranteService {
    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Restaurante %d não foi encontrado";
    private static final String MSG_ESTADO_EM_USO = "O Restaurante %s está em uso";
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante){
       return restauranteRepository.save(restaurante);

    }


    public void exluir(Long restauranteId){
        try{
            restauranteRepository.deleteById(restauranteId);
        }
        catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException("Restaurante " + restauranteId + "Não localizada");

        }

    }
}
