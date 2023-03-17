package com.algaworks.algafood.api.assembler;


import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModelAssembler {
    public RestauranteModel toModel(Restaurante restaurante){

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
