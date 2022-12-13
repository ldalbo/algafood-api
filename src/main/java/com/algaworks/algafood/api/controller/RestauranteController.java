package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")  // NIVEL DE CLASSE
public class RestauranteController {

    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    CadastroRestauranteService cadastroRestaurante;

    @Autowired
    CozinhaRepository cozinhaRepository;

    @GetMapping
    public ResponseEntity <List<Restaurante>> listar(){
       List<Restaurante> restaurantes = restauranteRepository.findAll();
       return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("{id}")
    public ResponseEntity <Restaurante> buscar(@PathVariable("id") Long RestauranteId){
        Optional<Restaurante> restaurante = restauranteRepository.findById(RestauranteId);
        if (restaurante.isEmpty()){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurante.get());

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



    @DeleteMapping("{id}")
    public ResponseEntity<?> remover(@PathVariable("id") Long restauranteId){
        try{
            cadastroRestaurante.exluir(restauranteId);
            return ResponseEntity.noContent().build();
        }
        catch (EntidadeNaoEncontradaException e){
            return  ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable("id") Long Id,@RequestBody Restaurante restaurante){

        // TEMOS A NOVA COZINHA
        Long cozinhaId = restaurante.getCozinha().getId();

        // VER SE ESSA COZINHA EXISTE
        Optional<Cozinha> cozinhaSalva = cozinhaRepository.findById(cozinhaId);

        if (cozinhaSalva.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(Id);
        if (restauranteAtual.isPresent()){


            BeanUtils.copyProperties(restaurante,restauranteAtual.get(),"id","formaPagamento");
            Restaurante restauranteNovo = cadastroRestaurante.salvar(restauranteAtual.get());
            return ResponseEntity.ok(restauranteAtual.get());

        }

        return ResponseEntity.notFound().build();


    }


}


