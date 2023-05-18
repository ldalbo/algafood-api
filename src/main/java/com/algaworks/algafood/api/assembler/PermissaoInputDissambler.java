package com.algaworks.algafood.api.assembler;



import com.algaworks.algafood.api.model.input.PermissaoInput;
import com.algaworks.algafood.domain.model.Permissao;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissaoInputDissambler {




    @Autowired
    ModelMapper modelMapper;

    // Aqui pelo que entendi, faço o mapeamento sem copiar
    public Permissao domainToObject(PermissaoInput permissaoInput){
        return modelMapper.map(permissaoInput, Permissao.class);
    }


    // Aqui faço a cópia da formaPagamentoInput para um objeto
    public void copyDomainToObject(PermissaoInput permissaoInput, Permissao permissao){
        // que copia volta void, por isso o que chama tb usa o void
        modelMapper.map(permissaoInput,permissao);

    }

}
