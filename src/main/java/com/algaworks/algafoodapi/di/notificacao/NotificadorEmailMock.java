package com.algaworks.algafoodapi.di.notificacao;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmailMock implements Notificador {

    public NotificadorEmailMock() {
        System.out.println("NotificadorEmail Mock");
    }
    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Simulando e-mail via Mock %s através do e-mail %s: %s",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
