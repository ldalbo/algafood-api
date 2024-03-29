package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/estatisticas")
public class EstatisticasController {

    @Autowired
    VendaQueryService vendaQueryService;


    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> consultaVendasDiaria(VendaDiariaFilter filtro,
                                                  @RequestParam(defaultValue = "+00:00", required = false) String timeOffset){
        return vendaQueryService.consultarVendasDiarias(filtro,timeOffset);
    }

}
