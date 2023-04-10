package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {
    private static final String MSG_RESTAURANTE_EM_USO = "O Restaurante %s está em uso";
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Transactional
    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);

    }


    @Transactional
    public void exluir(Long restauranteId){
        try{
            restauranteRepository.deleteById(restauranteId);
            restauranteRepository.flush();
        }
        catch (EmptyResultDataAccessException e){
            throw new RestauranteNaoEncontradoException( restauranteId);
        }
        catch (DataIntegrityViolationException e){
            throw  new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO,restauranteId));
        }
    }


    public Restaurante buscarOuFalhar(Long id){
        return restauranteRepository.findById(id)
                .orElseThrow(() ->new RestauranteNaoEncontradoException(
                        id));
    }

    @Transactional
    public void ativar(Long restauranteId ){
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
        // Não precisa de save, pois o JPA já faz o commit
        restauranteAtual.ativar();
    }


    @Transactional
    public void inativar(Long restauranteId ){
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);
        // Não precisa de save, pois o JPA já faz o commit
        restauranteAtual.inativar();
    }
}
