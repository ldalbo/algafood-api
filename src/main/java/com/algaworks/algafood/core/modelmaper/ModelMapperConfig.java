package com.algaworks.algafood.core.modelmaper;

import com.algaworks.algafood.api.model.RestauranteModel;
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
        return new ModelMapper();
    }
}
