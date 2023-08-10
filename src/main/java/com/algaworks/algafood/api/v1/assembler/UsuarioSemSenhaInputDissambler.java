package com.algaworks.algafood.api.v1.assembler;


import com.algaworks.algafood.api.v1.model.input.UsuarioSemSenhaInput;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioSemSenhaInputDissambler {

    @Autowired
    ModelMapper modelMapper;

    // Aqui pelo que entendi, faço o mapeamento sem copiar
    // Tranformo o Json que entra em Objeto
    public Usuario domainToObject(UsuarioSemSenhaInput usuarioSemSenhaInput){
        return modelMapper.map(usuarioSemSenhaInput, Usuario.class);
    }


    // Aqui faço a cópia da usuarioInput para um objeto
    // como o segundo parametro é 1 objeto, a alteração dele
    // reflete para quem chama
    public void copyDomainToObject(UsuarioSemSenhaInput usuarioSemSenhaInput, Usuario usuario){
        // que copia volta void, por isso o que chama tb usa o void
        modelMapper.map(usuarioSemSenhaInput,usuario);

    }

}
