package com.algaworks.algafood.api.controller;



import com.algaworks.algafood.api.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")  // NIVEL DE CLASSE
public class GrupoPermissaoController {

    @Autowired
    GrupoService cadastroGrupo;

    @Autowired
    PermissaoModelAssembler permissaoModelAssembler;



    @GetMapping
    public List<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
        Collection<Permissao> permissoes = grupo.getPermissoes();
        return permissaoModelAssembler.toCollectionModel(permissoes);

    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId){
        cadastroGrupo.associarPermissao(grupoId,permissaoId);
    }



    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId){
        cadastroGrupo.deassociarPermissao(grupoId,permissaoId);
    }


}


