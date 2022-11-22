package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import com.algaworks.algafoodapi.di.notificacao.Notificador;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    private Notificador notificador;


    public void ativar(Cliente cliente){
        cliente.ativar();
        this.notificador.notificar(cliente,"Seu cadastro está ativo no sistema");
    }


    
    public AtivacaoClienteService(Notificador notificador) {
        System.out.println("AtivacaoClienteService " + notificador);
        this.notificador = notificador;
    }
}
