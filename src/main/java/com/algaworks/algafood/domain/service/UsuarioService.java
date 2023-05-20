package com.algaworks.algafood.domain.service;


import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioEmUsoException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.exception.UsuarioSenhaInvalidaException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;


@Service
public class UsuarioService {
    private static final String MSG_USUARIO_EM_USO = "O Usuário %s está em uso";

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    GrupoService cadastroGrupo;

    @Transactional
    public Usuario salvar(Usuario usuario){
        // entityManager.detach(usuario);

        usuarioRepository.detach(usuario);

        // ANTES DE CONSULTA, O JPA JÁ SALVA, PARA GARANTIR INTEGRIDADE
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() &&  !usuario.equals(usuarioExistente.get())) {
            throw new NegocioException(String.format("Este e-mail já está em uso %s",usuario.getEmail()));

        }

        return usuarioRepository.save(usuario);

    }

    // Se não for colocado @Transactional o "setNovaSenha" não funciona
    @Transactional
    public void trocaSenha(Long usuarioId, String senhaAtual, String novaSenha){
        Usuario usuario = buscarOuFalhar(usuarioId);
        if (!usuario.getSenha().equals(senhaAtual)){
            throw new UsuarioSenhaInvalidaException("Senha digitada não confere com a senha atual");
        }
        if (senhaAtual.equals(novaSenha)){
            throw new UsuarioSenhaInvalidaException("Senha nova não pode ser igual da antiga");
        }
        usuario.setSenha(novaSenha);


    }

    @Transactional
    public void excluir(Long id){
        try {
            usuarioRepository.deleteById(id);
            usuarioRepository.flush();
        }
        catch(EmptyResultDataAccessException e){
            throw new UsuarioNaoEncontradoException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new UsuarioEmUsoException(String.format(MSG_USUARIO_EM_USO,id));

        }

    }

    public Usuario buscarOuFalhar(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(()-> new UsuarioNaoEncontradoException(id));
    }

    @Transactional
    public void associarGrupo(Long usuarioId, Long grupoId){
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
        usuario.associaciarGrupo(grupo);
    }

    @Transactional
    public void desassociarGrupo(Long usuarioId, Long grupoId){
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
        usuario.desassociarGrupo(grupo);
    }



}
