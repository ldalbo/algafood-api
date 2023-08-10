package com.algaworks.algafood.api.v1.assembler;


import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


// 
@Component
public class CozinhaModelAssembler {


    @Autowired
    private ModelMapper modelMapper;

    // Aqui eu pego os dados dentro da aplicaçào e devolvo o json
    public CozinhaModel toModel(Cozinha cozinha){
        return modelMapper.map(cozinha, CozinhaModel.class );
    }


    public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(cozinha -> toModel(cozinha))
                .collect(Collectors.toList());
    }
}
