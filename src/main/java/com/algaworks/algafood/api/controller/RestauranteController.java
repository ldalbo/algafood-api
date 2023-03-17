package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;

import com.algaworks.algafood.api.model.RestauranteModel;

import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")  // NIVEL DE CLASSE
public class RestauranteController {

    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    CadastroRestauranteService cadastroRestaurante;

    @Autowired
    RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    RestauranteInputDisassembler restauranteInputDisassembler;

    @GetMapping
    public List<RestauranteModel> listar(){
       List<Restaurante> restaurantes = restauranteRepository.findAll();

        //List<RestauranteModel> restaurantesModel = new ArrayList<RestauranteModel>();
        // Não preciso tipar o ArrayList
        List<RestauranteModel> restaurantesModel = new ArrayList<>();
        for (Restaurante restaurante : restaurantes) {
            restaurantesModel.add(restauranteModelAssembler.toModel(restaurante));
        }
        return restaurantesModel;

    }

    @GetMapping("{id}")
    public RestauranteModel buscar(@PathVariable("id") Long restauranteId){

        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        return restauranteModelAssembler.toModel(restaurante);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {

        try {
            Restaurante restaurante = new Restaurante();
            restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable  Long restauranteId,
                                 @RequestBody @Valid RestauranteInput restauranteInput) {
        try {


            Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
            Restaurante restaurante = new Restaurante();
            restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

            BeanUtils.copyProperties(restaurante, restauranteAtual,
                    "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException e) {

            throw new NegocioException(e.getMessage());
        }
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long restauranteId){
           cadastroRestaurante.exluir(restauranteId);

    }








}


