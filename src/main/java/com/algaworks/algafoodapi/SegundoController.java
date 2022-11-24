package com.algaworks.algafoodapi;

import com.algaworks.algafoodapi.di.modelo.Cliente;
import com.algaworks.algafoodapi.di.service.AtivacaoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SegundoController {
    @Autowired
    private AtivacaoClienteService ativacaoClienteService;

/*
    public SegundoController(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;
        System.out.println("Entrando em MeuPrimeiroController" + ativacaoClienteService);
    }
*/

    @GetMapping("/ativa")
    @ResponseBody
    public String Hello(){
        Cliente joao = new Cliente("Maria","email@maria","54999968836");
        ativacaoClienteService.ativar(joao);

        return "Ativado via segundo controller";
    }

}
