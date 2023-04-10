package com.algaworks.algafood.api.assembler;


import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// 
@Component
public class FormaPagamentoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    // Aqui eu pego os dados dentro da aplicaçào e devolvo o json
    public FormaPagamentoModel toModel(FormaPagamento formaPagamento){
        return modelMapper.map(formaPagamento, FormaPagamentoModel.class );
    }

}
