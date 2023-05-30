package com.algaworks.algafood.core.modelmaper;

import com.algaworks.algafood.api.model.EnderecoModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.ItemPedidoInput;
import com.algaworks.algafood.api.model.input.UsuarioSenhaInput;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.ItemPedido;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// É DIZER PARA O SPRING
// GERENCIE ESTE BEAN
// AI ELE PRECISA DE UMA ESPÉCIE DE CONSTRUTOR

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();



//		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//			.addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);

/*
        modelMapper.createTypeMap(UsuarioSenhaInput.class, Usuario.class)
			.addMapping(UsuarioSenhaInput::getSenhaNova, Usuario::setSenha);
*/
        // Aqui é para que o produtoId não seja jogado como id do ItemPedido e deixe
        // funcionar o auto-generator
        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        var senhaNovaInputToUsuarioTypeMap = modelMapper.createTypeMap(
                UsuarioSenhaInput.class, Usuario.class);

        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
                Endereco.class, EnderecoModel.class);

        // lambda
        enderecoToEnderecoModelTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                ((enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value))
                                                 );

        return modelMapper;

    }
}
