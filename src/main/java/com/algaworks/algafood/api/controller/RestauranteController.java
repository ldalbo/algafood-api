package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
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



    @GetMapping
    public List<RestauranteModel> listar(){
       List<Restaurante> restaurantes = restauranteRepository.findAll();

        //List<RestauranteModel> restaurantesModel = new ArrayList<RestauranteModel>();
        // Não preciso tipar o ArrayList
        List<RestauranteModel> restaurantesModel = new ArrayList<>();
        for (Restaurante restaurante : restaurantes) {
            restaurantesModel.add(toModel(restaurante));
        }
        return restaurantesModel;

    }

    @GetMapping("{id}")
    public RestauranteModel buscar(@PathVariable("id") Long restauranteId){

        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        return toModel(restaurante);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid Restaurante restaurante) {

        try {
            return toModel(cadastroRestaurante.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable  Long restauranteId,
                                 @RequestBody @Valid Restaurante restaurante) {
        try {

            Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

            BeanUtils.copyProperties(restaurante, restauranteAtual,
                    "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

            return toModel(cadastroRestaurante.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException e) {

            throw new NegocioException(e.getMessage());
        }
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long restauranteId){
           cadastroRestaurante.exluir(restauranteId);

    }


    private RestauranteModel toModel(Restaurante restaurante){

        RestauranteModel restauranteModel = new RestauranteModel();
        Cozinha cozinha = restaurante.getCozinha();

        CozinhaModel cozinhaModel = new CozinhaModel();
        cozinhaModel.setId(cozinha.getId());
        cozinhaModel.setNome(cozinha.getNome());
        restauranteModel.setCozinha(cozinhaModel);
        restauranteModel.setId(restaurante.getId());
        restauranteModel.setNome(restaurante.getNome());
        restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());

        return restauranteModel;



    }





}


