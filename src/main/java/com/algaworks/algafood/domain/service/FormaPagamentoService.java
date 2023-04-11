package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.FormaPagamentoEmUsoException;
import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormaPagamentoService {
    private static final String MSG_ESTADO_EM_USO = "A Forma de Pagamento %s está em uso";

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento){
        return formaPagamentoRepository.save(formaPagamento);

    }
    @Transactional
    public void excluir(Long id){
        try {
            formaPagamentoRepository.deleteById(id);
            formaPagamentoRepository.flush();
        }
        catch(EmptyResultDataAccessException e){
            throw new FormaPagamentoNaoEncontradaException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new FormaPagamentoEmUsoException(String.format(MSG_ESTADO_EM_USO,id));

        }

    }

    public FormaPagamento buscarOuFalhar(Long id){
        return formaPagamentoRepository.findById(id)
                .orElseThrow(()-> new FormaPagamentoNaoEncontradaException(id));
    }

}
