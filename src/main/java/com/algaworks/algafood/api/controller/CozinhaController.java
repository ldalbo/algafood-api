package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
// AQUI FICA O DIRETÓRIO RAIZ O QUE VIRIA DEPOIS DO "localhost:8080"
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    // ACRESCENTA NO FINAL DA PRINCIPAL, NESSE CASO NADA

    @GetMapping
    public List<Cozinha> listar(){
       return  cozinhaRepository.findAll();

   }


    @GetMapping("{id}") //AQUI PODE SER QUALQUER NOME
    public Cozinha buscar(@PathVariable("id") Long cozinhaId){ // explicito é dizer "Pegue do Mapping o "id"

        return cadastroCozinha.buscarOuFalhar(cozinhaId);

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
       return cadastroCozinha.salvar(cozinha);
    }

    @PutMapping("{id}") // PARA PUT pode ser os 2 "/{id}  ou "{id}"
    public Cozinha atualizar(@PathVariable("id") Long cozinhaId, @RequestBody Cozinha cozinha){

        Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
        BeanUtils.copyProperties(cozinha,cozinhaAtual,"id");
        return  cadastroCozinha.salvar(cozinhaAtual);

    }

     @DeleteMapping("{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void remove(@PathVariable("id") Long cozinhaId){
        cadastroCozinha.excluir(cozinhaId);
     }



}
