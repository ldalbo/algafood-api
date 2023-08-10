package com.algaworks.algafood.api.v1.controller;


import com.algaworks.algafood.api.v1.assembler.PermissaoInputDissambler;
import com.algaworks.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.v1.model.PermissaoModel;
import com.algaworks.algafood.api.v1.model.input.PermissaoInput;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.domain.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {



    @Autowired
    private PermissaoService cadastroPermissao;

    @Autowired
    private PermissaoInputDissambler permissaoInputDissambler;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @GetMapping
    public List<PermissaoModel>  listar(){
        List<Permissao> permissoes = permissaoRepository.findAll();
        return permissaoModelAssembler.toCollectionModel(permissoes);
    }


    @GetMapping("/{id}")
    public PermissaoModel get(@PathVariable Long id){
        Permissao permissao = cadastroPermissao.buscarOuFalhar(id);
        return permissaoModelAssembler.toModel(permissao);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissaoModel salvar(@RequestBody @Valid PermissaoInput permissaoInput){
        Permissao permissao = permissaoInputDissambler.domainToObject(permissaoInput);
        permissao = cadastroPermissao.salvar(permissao);
        return permissaoModelAssembler.toModel(permissao);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable("id") Long permissaoId){

        cadastroPermissao.excluir(permissaoId);

    }








}
