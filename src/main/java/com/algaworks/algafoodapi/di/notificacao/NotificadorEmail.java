package com.algaworks.algafoodapi.di.notificacao;

import com.algaworks.algafoodapi.di.modelo.Cliente;



public class NotificadorEmail implements Notificador {
    private boolean caixaAlta;

    public NotificadorEmail(String hostServidorSMTP) {
        this.hostServidorSMTP = hostServidorSMTP;
        System.out.println("NotificadorEmail");

    }

    private String hostServidorSMTP;

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        if (this.caixaAlta){
            mensagem = mensagem.toUpperCase();
        }
        System.out.printf("Notificando %s através do e-mail %s: %s pelo servidor %s",
                cliente.getNome(), cliente.getEmail(), mensagem, this.hostServidorSMTP);
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }


}
