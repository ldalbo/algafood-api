package com.algaworks.algafood.api.assembler;


import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// 
@Component
public class CozinhaModelAssembler {


    @Autowired
    private ModelMapper modelMapper;

    // Aqui eu pego os dados dentro da aplicaçào e devolvo o json
    public CozinhaModel toModel(Cozinha cozinha){
        return modelMapper.map(cozinha, CozinhaModel.class );
    }

}
