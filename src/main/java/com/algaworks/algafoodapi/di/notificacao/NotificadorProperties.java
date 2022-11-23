package com.algaworks.algafoodapi.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
public class NotificadorProperties {

    private String hostServidor;



    private String portaServidor;

    public void setPortaServidor(String portaServidor) {
        this.portaServidor = portaServidor;
    }
    public String getPortaServidor() {
        return portaServidor;
    }



    public String getHostServidor() {
        return hostServidor;
    }

    public void setHostServidor(String hostServidor) {
        this.hostServidor = hostServidor;
    }







}
