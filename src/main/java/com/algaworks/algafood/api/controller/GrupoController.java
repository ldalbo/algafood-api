package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.GrupoInputDissambler;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoService cadastroGrupo;

    @Autowired
    private GrupoInputDissambler grupoInputDissambler;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @GetMapping
    public List<GrupoModel>  listar(){
        List<Grupo> grupos = grupoRepository.findAll();


        List<GrupoModel> listaGrupo = new ArrayList<GrupoModel>();

        for(Grupo grupo : grupos ){
            listaGrupo.add(grupoModelAssembler.toModel(grupo));
        }
        return listaGrupo;
    }


    @GetMapping("/{id}")
    public GrupoModel get(@PathVariable Long id){

        Grupo grupo = cadastroGrupo.buscarOuFalhar(id);

        return grupoModelAssembler.toModel(grupo);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel salvar(@RequestBody @Valid GrupoInput grupoInput){
        Grupo grupo = new Grupo();
        grupo = grupoInputDissambler.domainToObject(grupoInput);
        Grupo grupoSalvo = cadastroGrupo.salvar(grupo);
        return grupoModelAssembler.toModel(grupoSalvo);
    }



    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GrupoModel atualizar(@RequestBody @Valid GrupoInput grupoInput,@PathVariable("id") Long id){
        Grupo grupoSalvo = cadastroGrupo.buscarOuFalhar(id);
        grupoInputDissambler.copyDomainToObject(grupoInput,grupoSalvo);
        return grupoModelAssembler.toModel(grupoSalvo);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        cadastroGrupo.excluir(id);

    }

}
