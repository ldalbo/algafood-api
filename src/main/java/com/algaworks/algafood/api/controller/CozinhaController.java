package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    CozinhaRepository cozinhaRepository;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE )

    public List<Cozinha> listar(){
        return cozinhaRepository.todas();
    }
    /* PRIMEIRA  FORMA EXPLICITANDO
    @GetMapping("/{cozinhaId}") //AQUI PODE SER QUALQUER NOME
    public Cozinha buscar(@PathVariable("cozinhaId") Long id){
        return  cozinhaRepository.porId(id);
    }

     */

    @GetMapping("/{cozinhaId}") //AQUI PODE SER QUALQUER NOME
    public Cozinha buscar(@PathVariable Long cozinhaId){
        return  cozinhaRepository.porId(cozinhaId);
    }





}
