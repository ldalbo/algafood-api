package com.algaworks.algafood.api.v1.controller;




import com.algaworks.algafood.api.v1.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.GrupoService;
import com.algaworks.algafood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")  // NIVEL DE CLASSE
public class UsuarioGrupoController {

    @Autowired
    GrupoService cadastroGrupo;

    @Autowired
    UsuarioService cadastroUsuario;

    @Autowired
    GrupoModelAssembler grupoModelAssembler;


    @GetMapping
    public List<GrupoModel> listar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
        Collection<Grupo> grupos = usuario.getGrupos();
        return grupoModelAssembler.toCollectionModel(grupos);

    }


    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        cadastroUsuario.associarGrupo(usuarioId,grupoId);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        cadastroUsuario.desassociarGrupo(usuarioId,grupoId);
    }


}


