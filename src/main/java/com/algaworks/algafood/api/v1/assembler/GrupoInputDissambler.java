package com.algaworks.algafood.api.v1.assembler;


import com.algaworks.algafood.api.v1.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoInputDissambler {

    @Autowired
    ModelMapper modelMapper;

    // Aqui pelo que entendi, faço o mapeamento sem copiar
    // Tranformo o Json que entra em Objeto
    public Grupo domainToObject(GrupoInput grupoInput){
        return modelMapper.map(grupoInput, Grupo.class);
    }


    // Aqui faço a cópia da grupoInput para um objeto
    // como o segundo parametro é 1 objeto, a alteração dele
    // reflete para quem chama
    public void copyDomainToObject(GrupoInput grupoInput, Grupo grupo){
        // que copia volta void, por isso o que chama tb usa o void
        modelMapper.map(grupoInput,grupo);

    }

}
