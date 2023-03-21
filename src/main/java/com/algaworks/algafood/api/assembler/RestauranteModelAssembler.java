package com.algaworks.algafood.api.assembler;


import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public RestauranteModel toModel(Restaurante restaurante){
        return modelMapper.map(restaurante, RestauranteModel.class);
    }


}
