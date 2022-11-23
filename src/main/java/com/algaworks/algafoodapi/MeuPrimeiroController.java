package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import com.algaworks.algafoodapi.di.service.AtivacaoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {
    @Autowired
    private AtivacaoClienteService ativacaoClienteService;


    public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;
        System.out.println("Entrando em MeuPrimeiroController" + ativacaoClienteService);
    }


    @GetMapping("/hello")
    @ResponseBody
    public String Hello(){
        Cliente joao = new Cliente("Joao","email@joao","54999968836");
        ativacaoClienteService.ativar(joao);

        return "Hello";
    }

}
