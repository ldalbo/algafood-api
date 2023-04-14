package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.UsuarioInputDissambler;
import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.assembler.UsuarioSemSenhaInputDissambler;
import com.algaworks.algafood.api.assembler.UsuarioSenhaInputDissambler;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.api.model.input.UsuarioSemSenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioSenhaInput;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService cadastroUsuario;

    @Autowired
    private UsuarioInputDissambler usuarioInputDissambler;

    @Autowired
    private UsuarioSenhaInputDissambler usuarioSenhaInputDissambler;

    @Autowired
    private UsuarioSemSenhaInputDissambler usuarioSemSenhaInputDissambler;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @GetMapping
    public List<UsuarioModel>  listar(){
        List<Usuario> usuarios = usuarioRepository.findAll();


        List<UsuarioModel> listaUsuario = new ArrayList<UsuarioModel>();

        for(Usuario usuario : usuarios ){
            listaUsuario.add(usuarioModelAssembler.toModel(usuario));
        }
        return listaUsuario;
    }


    @GetMapping("/{id}")
    public UsuarioModel get(@PathVariable Long id){

        Usuario usuario = cadastroUsuario.buscarOuFalhar(id);

        return usuarioModelAssembler.toModel(usuario);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel salvar(@RequestBody @Valid UsuarioInput usuarioInput){
        Usuario usuario = new Usuario();
        usuario = usuarioInputDissambler.domainToObject(usuarioInput);
        Usuario usuarioSalvo = cadastroUsuario.salvar(usuario);
        return usuarioModelAssembler.toModel(usuarioSalvo);
    }



    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioModel atualizar(@RequestBody @Valid UsuarioSemSenhaInput usuarioSemSenhaInput, @PathVariable("id") Long id){
        Usuario usuarioSalvo = cadastroUsuario.buscarOuFalhar(id);
        usuarioSemSenhaInputDissambler.copyDomainToObject(usuarioSemSenhaInput,usuarioSalvo);
        usuarioSalvo = cadastroUsuario.salvar(usuarioSalvo);
        return usuarioModelAssembler.toModel(usuarioSalvo);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        cadastroUsuario.excluir(id);

    }


    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void trocaSenha(@PathVariable  Long usuarioId,
                           @RequestBody @Valid UsuarioSenhaInput usuarioSenhaInput) {
        try {

            Usuario usuarioSalvo = cadastroUsuario.buscarOuFalhar(usuarioId);
            cadastroUsuario.senhaOK(usuarioSenhaInput,usuarioSalvo);
            usuarioSalvo.setSenha(usuarioSenhaInput.getSenhaNova());

            cadastroUsuario.salvar(usuarioSalvo);
          }

        catch (UsuarioNaoEncontradoException e ) {
            throw new NegocioException(e.getMessage());
        }
    }

}
