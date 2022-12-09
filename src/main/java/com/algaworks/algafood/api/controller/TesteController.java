package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    CozinhaRepository cozinhaRepository;

    // INDO POR NOMES IGUAIS
    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha>  listarPorNome(@RequestParam("nome") String nome){
        System.out.println("Controller");
        return cozinhaRepository.findAll();

    }


/*   EXPLICITANDO OS PARAMETROS
     @GetMapping("/cozinhas/por-nome")
    public List<Cozinha>  listarPorNome(@RequestParam("nome") String nomeRestaurante){
        System.out.println("Controller");
        return cozinhaRepository.consultaPorNome(nomeRestaurante);

    }*/
}
