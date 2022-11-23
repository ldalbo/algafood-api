package com.algaworks.algafoodapi.listener;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import com.algaworks.algafoodapi.di.notificacao.NivelUrgencia;
import com.algaworks.algafoodapi.di.notificacao.Notificador;
import com.algaworks.algafoodapi.di.notificacao.TipoDoNotificador;
import com.algaworks.algafoodapi.di.service.ClienteAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {
    @TipoDoNotificador(NivelUrgencia.URGENCIA)
    @Autowired
    private Notificador notificador;

    @EventListener
    public void clienteAtivadoListner(ClienteAtivadoEvent event){
        Cliente cliente =  event.getCliente();
        System.out.println("Cliente ativado via Listner " +  " " + cliente.getNome() + " agora está ativo" );
        notificador.notificar(cliente, " Mensagem vinda do Notificador de ativação");
    }
}
