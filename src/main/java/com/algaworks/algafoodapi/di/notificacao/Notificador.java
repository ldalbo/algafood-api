package com.algaworks.algafoodapi.di.notificacao;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import org.springframework.stereotype.Component;


public interface Notificador {
    void notificar(Cliente cliente, String mensagem);


}
