package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.CozinhaInputDissambler;
import com.algaworks.algafood.api.v1.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
// AQUI FICA O DIRETÓRIO RAIZ O QUE VIRIA DEPOIS DO "localhost:8080"
@RequestMapping("/v1/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDissambler cozinhaInputDissambler;

    // ACRESCENTA NO FINAL DA PRINCIPAL, NESSE CASO NADA



   @GetMapping
    public Page<CozinhaModel> listar(Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
        List<CozinhaModel> cozinhasModel = cozinhaModelAssembler.toCollectionModel(cozinhasPage.getContent());
        return new PageImpl<>(cozinhasModel, pageable, cozinhasPage.getTotalElements());
    }



    @GetMapping("{id}") //AQUI PODE SER QUALQUER NOME
    public CozinhaModel buscar(@PathVariable("id") Long cozinhaId){ // explicito é dizer "Pegue do Mapping o "id"

        return cozinhaModelAssembler.toModel(cadastroCozinha.buscarOuFalhar(cozinhaId));

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput){
        try{
            // Do input transformo em objeto
            Cozinha cozinha = cadastroCozinha.salvar(cozinhaInputDissambler.domainToObject(cozinhaInput));
            // do objeto transformo em dominio
            return cozinhaModelAssembler.toModel(cozinha);
        }
        catch (CozinhaNaoEncontradaException e ){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @PutMapping("{id}") // PARA PUT pode ser os 2 "/{id}  ou "{id}"
    public CozinhaModel atualizar(@PathVariable("id") Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput){

        Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
        cozinhaInputDissambler.copyDomainToObject(cozinhaInput,cozinhaAtual);
        return cozinhaModelAssembler.toModel(cozinhaAtual);


    }

     @DeleteMapping("{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void remove(@PathVariable("id") Long cozinhaId){
        cadastroCozinha.excluir(cozinhaId);
     }



}
