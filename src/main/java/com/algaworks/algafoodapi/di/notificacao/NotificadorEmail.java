package com.algaworks.algafoodapi.di.notificacao;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

    public NotificadorEmail() {
        System.out.println("Construtor NotificadorEmail");

    }



    @Override
    public void notificar(Cliente cliente, String mensagem) {

        System.out.printf("Notificando %s através do e-mail %s: %s",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }



}
