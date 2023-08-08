package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CidadeInputDissambler {


    @Autowired
    ModelMapper modelMapper;

    // Usado no post
    public Cidade domainToObject(CidadeInput cidadeInput){
        return modelMapper.map(cidadeInput, Cidade.class);
    }


    public void copyDomainToObject(CidadeInput cidadeInput, Cidade cidade){
        // que copia volta void, por isso o que chama tb usa o void
        modelMapper.map(cidadeInput,cidade);

    }








}
