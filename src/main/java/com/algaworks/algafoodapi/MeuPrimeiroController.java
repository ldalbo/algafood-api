package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import com.algaworks.algafoodapi.di.service.AtivacaoClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {

    public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;
        System.out.println("Entrando em MeuPrimeiroController" + ativacaoClienteService);
    }

    private AtivacaoClienteService ativacaoClienteService;


    @GetMapping("/hello")
    @ResponseBody
    public String Hello(){
        Cliente joao = new Cliente("Joao","email@joao","54999968836");
        ativacaoClienteService.ativar(joao);

        return "Hello";
    }

}
