package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.di.notificacao.NotificadorEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificadorConfig {

    @Bean
    public NotificadorEmail notificadorEmail(){ // o nome do método não importa
        NotificadorEmail notificador =  new NotificadorEmail("www.teste.com.br");
        notificador.setCaixaAlta(true);
        return notificador;

    }




}
