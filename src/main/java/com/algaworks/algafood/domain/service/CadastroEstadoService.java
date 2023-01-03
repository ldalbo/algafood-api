package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    private static final String MSG_ESTADO_EM_USO = "O Estado %s está em uso";

    @Autowired
    EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void excluir(Long id){
        try{
            estadoRepository.deleteById(id);
        }

        catch (EmptyResultDataAccessException e){
            throw new EstadoNaoEncontradoException(id);
        }

        catch (DataIntegrityViolationException e){
            throw new EstadoNaoEncontradoException(String.format(MSG_ESTADO_EM_USO,id));
        }
    }

    public Estado buscarOuFalhar(Long id){
        return estadoRepository.findById(id)
                .orElseThrow(()->new EstadoNaoEncontradoException(id));

    }







}
