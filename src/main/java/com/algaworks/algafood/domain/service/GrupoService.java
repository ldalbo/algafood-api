package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.FormaPagamentoEmUsoException;
import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafood.domain.exception.GrupoEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrupoService {
    private static final String MSG_GRUPO_EM_USO = "O grupo de permissão %s está em uso";

    @Autowired
    GrupoRepository grupoRepository;

    @Transactional
    public Grupo salvar(Grupo grupo){
        return grupoRepository.save(grupo);

    }
    @Transactional
    public void excluir(Long id){
        try {
            grupoRepository.deleteById(id);
            grupoRepository.flush();
        }
        catch(EmptyResultDataAccessException e){
            throw new GrupoNaoEncontradoException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new GrupoEmUsoException(String.format(MSG_GRUPO_EM_USO,id));

        }

    }

    public Grupo buscarOuFalhar(Long id){
        return grupoRepository.findById(id)
                .orElseThrow(()-> new GrupoNaoEncontradoException(id));
    }

}
