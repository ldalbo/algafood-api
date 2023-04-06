package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CidadeInputDissambler {


    @Autowired
    ModelMapper modelMapper;

    // Usado no post
    public Cozinha domainToObject(CozinhaInput cozinhaInput){
        return modelMapper.map(cozinhaInput, Cozinha.class);
    }


    public void copyDomainToObject(CozinhaInput cozinhaInput, Cozinha cozinha){
        // que copia volta void, por isso o que chama tb usa o void
        modelMapper.map(cozinhaInput,cozinha);

    }








}
