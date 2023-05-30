package com.algaworks.algafood.api.assembler;



import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoInputDissambler {




    @Autowired
    ModelMapper modelMapper;

    // Aqui pelo que entendi, faço o mapeamento sem copiar
    public Pedido domainToObject(PedidoInput pedidoInput){
        return modelMapper.map(pedidoInput, Pedido.class);
    }


    // Aqui faço a cópia da formaPagamentoInput para um objeto
    public void copyDomainToObject(PedidoInput pedidoInput, Pedido pedido){
        modelMapper.map(pedidoInput,pedido);

    }

}
