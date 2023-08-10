package com.algaworks.algafood.api.v2.assembler;


import com.algaworks.algafood.api.v2.model.CidadeModelV2;
import com.algaworks.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class CidadeModelAssemblerV2 {

    @Autowired
    private ModelMapper modelMapper;


    public CidadeModelV2 toModel(Cidade cidade){
        return modelMapper.map(cidade, CidadeModelV2.class);
    }


    public List<CidadeModelV2> toCollectionModel(Collection<Cidade> cidades) {
        return cidades.stream()
                .map(cidade -> toModel(cidade))
                .collect(Collectors.toList());
    }



}
