package com.algaworks.algafood.api.v1.assembler;


import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;

import com.algaworks.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoInputDissambler {




    @Autowired
    ModelMapper modelMapper;

    // Aqui pelo que entendi, faço o mapeamento sem copiar
    public FormaPagamento domainToObject(FormaPagamentoInput formaPagamentoInput){
        return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
    }


    // Aqui faço a cópia da formaPagamentoInput para um objeto
    public void copyDomainToObject(FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamento){
        // que copia volta void, por isso o que chama tb usa o void
        modelMapper.map(formaPagamentoInput,formaPagamento);

    }

}
