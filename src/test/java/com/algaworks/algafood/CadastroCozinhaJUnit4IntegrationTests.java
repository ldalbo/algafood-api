package com.algaworks.algafood;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;




@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.algaworks.algafood.AlgafoodApiApplication.class)
// @SpringBootTest
public class CadastroCozinhaJUnit4IntegrationTests {

	@Autowired
	CadastroCozinhaService cadastroCozinha;

	@Test
	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
		// cenario # dados de input
		// no caso uma cozinha nova
		Cozinha novaCozinha = new Cozinha();
		String nomeCozinha = "Chinesa";
		novaCozinha.setNome(nomeCozinha);

		// ação # fazer o cadastro
		cadastroCozinha.salvar(novaCozinha);


		// validação, o que esperamos
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getNome()).isNotNull();


	}


	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void deveFalhar_QuandoCadastarCozinhaSemNome() {
		// cenario # dados de input
		// no caso uma cozinha nova
		Cozinha novaCozinha = new Cozinha();
		String nomeCozinha = null;
		novaCozinha.setNome(nomeCozinha);

		// ação # fazer o cadastro
		novaCozinha = cadastroCozinha.salvar(novaCozinha);
	}

	@Test(expected = CozinhaNaoEncontradaException.class)
	public void deveFalhar_QuandoCozinhaInexistente(){
		Long id = 100L;
		cadastroCozinha.excluir(id);
	}

	@Test(expected = EntidadeEmUsoException.class)
	public void deveFalhar_QuandoCozinhaEmUso(){
		Long id = 1L;
		cadastroCozinha.excluir(id);


	}



}
