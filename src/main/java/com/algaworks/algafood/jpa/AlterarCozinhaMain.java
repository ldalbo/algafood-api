package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class AlterarCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);


		// VIA CRIANDO OBJETO
		/*
		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);
		cozinha.setNome("Mexicana");
		cozinhaRepository.adicionar(cozinha);
		*/


		Cozinha cozinha = cozinhaRepository.porId(1L);
		cozinha.setNome("Mexicana");
		cozinhaRepository.adicionar(cozinha);

	}
	
}
