package com.algaworks.algafood.domain.service;


import com.algaworks.algafood.domain.exception.PermissaoEmUsoException;
import com.algaworks.algafood.domain.exception.PermissaoNaoEncontradaException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermissaoService {
    private static final String MSG_PERMISSAO_EM_USO = "A permissão %s está em uso";

    @Autowired
    PermissaoRepository permissaoRepository;
    @Transactional
    public Permissao salvar(Permissao permissao){
        return permissaoRepository.save(permissao);

    }
    @Transactional
    public void excluir(Long id){
        try {
            permissaoRepository.deleteById(id);
            permissaoRepository.flush();
        }
        catch(EmptyResultDataAccessException e){
            throw new PermissaoNaoEncontradaException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new PermissaoEmUsoException(String.format(MSG_PERMISSAO_EM_USO,id));

        }

    }

    public Permissao buscarOuFalhar(Long id){
        return permissaoRepository.findById(id)
                .orElseThrow(()-> new PermissaoNaoEncontradaException(id));
    }

}
