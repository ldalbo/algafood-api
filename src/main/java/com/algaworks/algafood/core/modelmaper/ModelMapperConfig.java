package com.algaworks.algafood.core.modelmaper;

import com.algaworks.algafood.api.model.EnderecoModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.Restaurante;
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
