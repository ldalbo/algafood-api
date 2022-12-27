package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    private static final String MSG_CIDADE_NAO_ENCONTRADA = "Cidade %d não encontrada ";

    private static final String MSG_CIDADE_EM_USO = "Cidade %d está em uso ";
    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    CadastroEstadoService cadastroEstado;

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }




    public void excluir(Long cidadeId){
       try{
           cidadeRepository.deleteById(cidadeId);
       }
       catch (EmptyResultDataAccessException e){
           throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId));
       }
       catch (DataIntegrityViolationException e){
           throw  new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO,cidadeId));
       }
    }

    public Cidade buscarOuFalhar(Long id){
       return cidadeRepository.findById(id)
               .orElseThrow(() ->new EntidadeNaoEncontradaException(
                       String.format(MSG_CIDADE_NAO_ENCONTRADA,id)));
    }
}
