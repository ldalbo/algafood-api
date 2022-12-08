package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class CrudEstadoMain {
    public static void main(String[] args){
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);


        EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);

        Estado  estadoMG = new Estado();
        estadoMG.setNome("Minas Gerais");
        estadoRepository.adicionar(estadoMG);

        Estado  estadoSP = new Estado();
        estadoSP.setNome("São Paulo");
        estadoRepository.adicionar(estadoSP);

        Estado estadoAL = estadoRepository.porId(3L);
        estadoAL.setNome("Alagoas");
        estadoRepository.adicionar(estadoAL);

        // estadoRepository.remover(estadoAL.getId());

        List<Estado> todosEstados = estadoRepository.todos();
        for (Estado estado : todosEstados){
            System.out.println("Estado " + estado.getNome());
        }



    }

}
