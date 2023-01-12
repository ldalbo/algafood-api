package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
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
    public ResponseEntity <Restaurante> buscar(@PathVariable("id") Long restauranteId){
        if (true){
            throw  new IllegalArgumentException("Ola");
        }
        Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);

        if (restaurante.isEmpty()){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurante.get());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
        try {
            return cadastroRestaurante.salvar(restaurante);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public Restaurante atualizar(@PathVariable Long restauranteId,
                                 @RequestBody  Restaurante restaurante) {
        try {
            Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

            BeanUtils.copyProperties(restaurante, restauranteAtual,
                    "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

            return cadastroRestaurante.salvar(restauranteAtual);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }


    @DeleteMapping("{id}")
    public void remover(@PathVariable("id") Long restauranteId){

            cadastroRestaurante.exluir(restauranteId);

    }





}


