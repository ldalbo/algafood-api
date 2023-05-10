package com.algaworks.algafood.api.assembler;


import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


//
@Component
public class ProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    // Aqui eu pego os dados dentro da aplicaçào e devolvo o json
    public ProdutoModel toModel(Produto produto){
        return modelMapper.map(produto, ProdutoModel.class );
    }

    // FORMA SEM LAMBDA
    /*
    public List<FormaPagamentoModel> toCollectionModel(Collection<FormaPagamento> formasPagamentos) {

        //List<FormaPagamentoModel> formasPagamento = new  ArrayList<FormaPagamentoModel>();
        // OU
        List<FormaPagamentoModel> formasPagamentoModel = new  ArrayList<>();

        List<FormaPagamentoModel> formaPagamentoModel = new ArrayList<>();
        for (FormaPagamento formasPagamento : formasPagamentos) {
            formasPagamentoModel.add(toModel(formasPagamento));
        }
        return formasPagamentoModel;
    }
    */

    public List<ProdutoModel> toCollectionModel(List<Produto> produtos) {
        return produtos.stream()
                .map(produto -> toModel(produto))
                .collect(Collectors.toList());
    }


}
