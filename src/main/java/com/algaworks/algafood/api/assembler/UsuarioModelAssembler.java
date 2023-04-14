package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// 
@Component
public class UsuarioModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    // Aqui eu pego o Objeto e devolbo Json
    public UsuarioModel toModel(Usuario usuario){
        return modelMapper.map(usuario, UsuarioModel.class );
    }

}
