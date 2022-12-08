package com.algaworks.algafood.jpa;


import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class CrudCidadeMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
        EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);


        cidadeRepository.remover(13L);


/*
        // RECUPERANDO RS e CRIANDO FLORES DA CUNHA
        Estado estadoRS = estadoRepository.porId(1L);
        Cidade cidadeFlores = new Cidade();
        cidadeFlores.setNome("Flores da Cunha");
        cidadeFlores.setEstado(estadoRS);
        cidadeRepository.adicionar(cidadeFlores);

        // CRIANDO O PARANÁ
        Estado estadoPR = new Estado();
        estadoPR.setNome("PR");
        estadoRepository.adicionar(estadoPR);

        // TROCAR FLORES -RS por CURITIBA - PR

        // PEGO FLORES DA CUNHA
        Cidade cidadeAtualizada = cidadeRepository.porId(3L);
        // MUDO PARA CURITIBA
        cidadeAtualizada.setNome("Curitiba");
        // MUDO O ESTADO PARA O PARANÁ
        cidadeAtualizada.setEstado(estadoRepository.porId(3L));

        // GRAVO A ATUALZIAÇÃO
        cidadeRepository.adicionar(cidadeAtualizada);
*/


        // REMOVO RECIFE E PERNAMBUCO
      //  cidadeRepository.remover(2L);
      //  estadoRepository.remover(2L);


        // NA LISTAGEM VAI SOBRAR CURITIBA e CAXIAS DO SUL
        List<Cidade> todasCidades = cidadeRepository.todos();
        for (Cidade cidade: todasCidades){
            System.out.println("Cidade " + cidade.getNome() +  " - " + cidade.getEstado().getNome());
        }






    }

}
