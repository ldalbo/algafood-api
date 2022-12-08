package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;

   public Cidade adicionar(Cidade cidade){
        return cidadeRepository.adicionar(cidade);
    }

    public void excluir(Long cidadeId){
       try{
           System.out.println("Service chamando Repository " + cidadeId);
           estadoRepository.remover(cidadeId);
       }
       catch (EmptyResultDataAccessException e){
           throw new EntidadeNaoEncontradaException("Cidade não encontrada " +   cidadeId);
       }


    }



}
