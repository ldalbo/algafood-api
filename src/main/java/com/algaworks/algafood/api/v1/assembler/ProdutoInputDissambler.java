package com.algaworks.algafood.api.v1.assembler;


import com.algaworks.algafood.api.v1.model.input.ProdutoInput;
import com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDissambler {




    @Autowired
    ModelMapper modelMapper;

    // Aqui pelo que entendi, faço o mapeamento sem copiar
    // Usado para método Post
    public Produto domainToObject(ProdutoInput produtoInput){
        return modelMapper.map(produtoInput, Produto.class);
    }


    // Aqui faço a cópia da formaPagamentoInput para um objeto
    // Usado para método Put
    public void copyDomainToObject(ProdutoInput produtoInput, Produto produto){
        // que copia volta void, por isso o que chama tb usa o void
        modelMapper.map(produtoInput,produto);

    }

}
