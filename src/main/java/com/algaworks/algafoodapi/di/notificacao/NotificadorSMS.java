package com.algaworks.algafoodapi.di.notificacao;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("urgente")
@Component
public class NotificadorSMS implements Notificador {

    public NotificadorSMS() {
        System.out.println("Notificador SMS");

    }


    @Override
    public void notificar(Cliente cliente, String mensagem) {

        System.out.printf("Notificando %s através do eTELEFONE %s: %s",
                cliente.getNome(), cliente.getTelefone(), mensagem);
    }




}
