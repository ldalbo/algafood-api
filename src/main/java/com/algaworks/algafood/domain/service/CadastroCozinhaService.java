package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CozinhaEmUsoException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCozinhaService {

    private static final String MSG_COZINHA_EM_USO = "A Cozinha %s está em uso";
    @Autowired
    private CozinhaRepository cozinhaRepository;

    // @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void excluir(Long id){
        try{
            System.out.println("VAI EXCLUIR");
            cozinhaRepository.deleteById(id);
            cozinhaRepository.flush();
        }
        catch(EmptyResultDataAccessException e){
            System.out.println("SEM COZINHA 2 #" +  e.toString() + "#");
            throw new CozinhaNaoEncontradaException(id);
        }
        catch (DataIntegrityViolationException e){
            System.out.println("COZINHA VIOLADA #" +  e.toString() + "#");
            throw new CozinhaEmUsoException(String.format(MSG_COZINHA_EM_USO,id));
        }
        catch (Exception e){
            System.out.println("GENERICA #" +  e.toString() + "#");
        }
  }

    public Cozinha buscarOuFalhar(Long id){
        System.out.println("buscarOuFalhar:" + id);
        return cozinhaRepository.findById(id)
                .orElseThrow(()-> new CozinhaNaoEncontradaException(id));

    }

}
