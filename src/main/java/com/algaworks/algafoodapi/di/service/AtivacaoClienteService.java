package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import com.algaworks.algafoodapi.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @Autowired
    private Notificador notificador;


    public void ativar(Cliente cliente){
        cliente.ativar();
        this.notificador.notificar(cliente,"Seu cadastro está ativo no sistema");
    }


/*    @Autowired
    public AtivacaoClienteService(Notificador notificador) {
        System.out.println("AtivacaoClienteService " + notificador);
        this.notificador = notificador;
    }
    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
    }

    public AtivacaoClienteService(String parametro) {

    }

    */


}
