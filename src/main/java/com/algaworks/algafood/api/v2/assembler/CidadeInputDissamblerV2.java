package com.algaworks.algafood.api.v2.assembler;

import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;
import com.algaworks.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CidadeInputDissamblerV2 {


    @Autowired
    ModelMapper modelMapper;

    // Usado no post
    public Cidade domainToObject(CidadeInputV2 cidadeInput){
        return modelMapper.map(cidadeInput, Cidade.class);
    }


    public void copyDomainToObject(CidadeInputV2 cidadeInput, Cidade cidade){
        // que copia volta void, por isso o que chama tb usa o void
        modelMapper.map(cidadeInput,cidade);

    }








}
