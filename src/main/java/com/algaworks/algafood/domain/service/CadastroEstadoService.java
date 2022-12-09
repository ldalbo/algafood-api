package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroEstadoService {

    @Autowired
    EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }



    public void excluir(Long estadoId){
        try{
            Optional<Estado> estado = estadoRepository.findById(estadoId);
            if (estado.isEmpty()){
                throw new EntidadeNaoEncontradaException("Estado não encontrado " + estadoId);
            }
            estadoRepository.deleteById(estadoId);

        }
        catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException("Entidade Estado " + estadoId + " está em uso " );
        }
    }






}
