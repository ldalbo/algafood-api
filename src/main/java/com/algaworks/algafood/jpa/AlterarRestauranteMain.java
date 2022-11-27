package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.infrastructure.repository.RestauranteRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class AlterarRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

        /* ABORDAGEM BUSCANDO TUDO E DEPOIS ATUALZIA */
        boolean updateFull = false;
        if (updateFull){
            Restaurante restaurante = restauranteRepository.porId(1L);
            restaurante.setNome("Fornello 2022");
            restauranteRepository.adicionar(restaurante);
        }
        else{
            Restaurante restaurante = new Restaurante();
            restaurante.setId(1L);
            restaurante.setNome("Fornello 2022");
            restauranteRepository.adicionar(restaurante);

        }





    }
}
