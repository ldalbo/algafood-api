package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// 
@Component
public class GrupoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    // Aqui eu pego o Objeto e devolbo Json
    public GrupoModel toModel(Grupo grupo){
        return modelMapper.map(grupo, GrupoModel.class );
    }

}
