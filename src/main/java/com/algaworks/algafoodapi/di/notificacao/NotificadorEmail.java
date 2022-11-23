package com.algaworks.algafoodapi.di.notificacao;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

    @Value("${notificador.email.hostname}")
    private String host;

    @Value("${notificador.email.port}")
    private Integer port;




    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Porta " + port );
        System.out.println("Host " +  host );


        System.out.printf("Notificando %s através do e-mail %s: %s",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }



}
