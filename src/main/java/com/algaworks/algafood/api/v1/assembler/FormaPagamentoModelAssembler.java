package com.algaworks.algafood.api.v1.assembler;



import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;

import com.algaworks.algafood.domain.model.FormaPagamento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



// 
@Component
public class FormaPagamentoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    // Aqui eu pego os dados dentro da aplicaçào e devolvo o json
    public FormaPagamentoModel toModel(FormaPagamento formaPagamento){
        return modelMapper.map(formaPagamento, FormaPagamentoModel.class );
    }

    // FORMA SEM LAMBDA
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

    /*
    public List<FormaPagamentoModel> toCollectionModel(List<FormaPagamento> formasPagamentos) {
        return formasPagamentos.stream()
                .map(formaPagamento -> toModel(formaPagamento))
                .collect(Collectors.toList());
    }
    */

}
