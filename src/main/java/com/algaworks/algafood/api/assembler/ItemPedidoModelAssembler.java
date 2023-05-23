package com.algaworks.algafood.api.assembler;


import com.algaworks.algafood.api.model.ItemPedidoModel;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.domain.model.ItemPedido;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ItemPedidoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;


    public ItemPedidoModel toModel(ItemPedido itemPedido){
        return modelMapper.map(itemPedido, ItemPedidoModel.class);
    }


    public List<ItemPedidoModel> toCollectionModel(Collection<ItemPedido> itensPedido) {
        return itensPedido.stream()
                .map(item -> toModel(item))
                .collect(Collectors.toList());
    }



}
