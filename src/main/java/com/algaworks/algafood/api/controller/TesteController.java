package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComFreteGratisSpec;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    CozinhaRepository cozinhaRepository;

    @Autowired
    RestauranteRepository restauranteRepository;

    // INDO POR NOMES IGUAIS
    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha>  listarPorNome(@RequestParam("nome") String nome){
        return cozinhaRepository.queryByNomeContaining(nome);
    }

    @GetMapping("/cozinhas/primeiro")
    public Optional<Cozinha>  getPrimeiraCozinha(){
        return cozinhaRepository.buscarPrimeiro();
    }


    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> porNome(@RequestParam("nome") String nome){
        return restauranteRepository.buscaPorNome(nome);
    }

    @GetMapping("/restaurantes/por-nome-cozinha")
    public List<Restaurante> porNomeCozinha(@RequestParam("nome") String nome, @RequestParam("cozinha") Long cozinhaId){
        return restauranteRepository.buscaPorNomeCozinha(nome,cozinhaId);
    }

    @GetMapping("/restaurantes/por-nome-taxa")
    public List<Restaurante> porNomeTaxa(@RequestParam("nome") String nome
            , @RequestParam("taxaInicial") BigDecimal taxaInicial
            , @RequestParam("taxaFinal") BigDecimal taxaFinal){
        return restauranteRepository.find(nome,taxaInicial,taxaFinal);
    }


    @GetMapping("/restaurantes/por-nome-taxa-gratis")
    public List<Restaurante> porNomeTaxaGratis(@RequestParam("nome") String nome
           ){

        var comNome = new RestauranteComNomeSemelhanteSpec(nome);
        var comTaxaGratis = new RestauranteComFreteGratisSpec();
        return restauranteRepository.findAll(comNome.or(comTaxaGratis));
    }


    @GetMapping("/restaurantes/primeiro")
    public Optional<Restaurante> getPrimeiro(){
        return restauranteRepository.buscarPrimeiro();
    }

}
