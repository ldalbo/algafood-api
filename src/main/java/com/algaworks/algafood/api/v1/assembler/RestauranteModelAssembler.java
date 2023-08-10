package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.model.RestauranteModel;
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
