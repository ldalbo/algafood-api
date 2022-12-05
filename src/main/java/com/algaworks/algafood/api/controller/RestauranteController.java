package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")  // NIVEL DE CLASSE
public class RestauranteController {

    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    public ResponseEntity <List<Restaurante>> listar(){
       List<Restaurante> restaurantes = restauranteRepository.todos();
       return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("{id}")
    public ResponseEntity <Restaurante> buscar(@PathVariable("id") Long RestauranteId){
        Restaurante restaurante = restauranteRepository.porId(RestauranteId);
        if (restaurante == null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurante);

    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante ){
        try{
            restaurante = cadastroRestaurante.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurante);
        }
        catch (EntidadeNaoEncontradaException e){
            return  ResponseEntity.badRequest()
                    .body(e.getMessage());
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@PathVariable("id") Long restauranteId,@RequestBody Restaurante restaurante){
        try{
            Restaurante restauranteAtual = restauranteRepository.porId(restauranteId);
            if (restauranteAtual == null){
                return ResponseEntity.notFound().build();

            }

            restauranteAtual.setNome(restaurante.getNome());
            restauranteAtual.setId(restauranteId);
            restauranteAtual.setTaxaEntrega(restaurante.getTaxaEntrega());
            restauranteAtual.setCozinha(restaurante.getCozinha());


            restaurante = cadastroRestaurante.salvar(restauranteAtual);
            return ResponseEntity.ok(restaurante);

        }
        catch (EntidadeNaoEncontradaException e){
            return  ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }






}
