package com.algaworks.algafoodapi.di.service;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import com.algaworks.algafoodapi.di.notificacao.NotificadorEmail;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    private NotificadorEmail notificador;


    public void ativar(Cliente cliente){
        cliente.ativar();
        this.notificador.notificar(cliente,"Seu cadastro estÃ¡ ativo no sistema");


    }
}
