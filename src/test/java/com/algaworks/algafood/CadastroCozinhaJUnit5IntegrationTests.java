package com.algaworks.algafood;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

// @ExtendWith(SpringExtension.class)
@SpringBootTest
public class CadastroCozinhaJUnit5IntegrationTests {

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Test
    public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
        // cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        // ação

        novaCozinha = cadastroCozinha.salvar(novaCozinha);

        // validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();



    }


    @Test
    public void deveFalhar_QuandoCadastarCozinhaSemNome() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        // SE TIVER ERRO ELE POPULA A erroEsperado
        ConstraintViolationException erroEsperado =
                Assertions.assertThrows(ConstraintViolationException.class, () -> {
                    cadastroCozinha.salvar(novaCozinha);
                });


        // AQUI CONFIRO SE NO PASSO ACIMA PEGOU ALGUM ERRO
        assertThat(erroEsperado).isNotNull();
    }


    @Test
    public void deveFalhar_QuandoCozinhaInexistente(){
        Long id = 100L;

        // SE TIVER ERRO ELE POPULA A erroEsperado
        CozinhaNaoEncontradaException erroEsperado =
                Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
                    cadastroCozinha.excluir(id);
                });
        // AQUI CONFIRO SE NO PASSO ACIMA PEGOU ALGUM ERRO
        assertThat(erroEsperado).isNotNull();
    }

    @Test
    public void deveFalhar_QuandoCozinhaEmUso(){
        Long id = 1L;

        // SE TIVER ERRO ELE POPULA A erroEsperado
        EntidadeEmUsoException erroEsperado =
                Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
                    cadastroCozinha.excluir(id);
                });
        // AQUI CONFIRO SE NO PASSO ACIMA PEGOU ALGUM ERRO
        assertThat(erroEsperado).isNotNull();
    }

}