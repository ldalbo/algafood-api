package com.algaworks.algafood.api.v1.controller;





import com.algaworks.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")  // NIVEL DE CLASSE
public class RestauranteUsuarioResponsavelController {

    @Autowired
    CadastroRestauranteService cadastroRestaurante;



    @Autowired
    UsuarioModelAssembler usuarioModelAssembler;


    @GetMapping
    public List<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        Collection<Usuario> responsaveis = restaurante.getResponsaveis();
        return  usuarioModelAssembler.toCollectionModel(responsaveis);


    }


    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionar(@PathVariable Long restauranteId, @PathVariable Long usuarioId){
        cadastroRestaurante.adicionarResponsavel(restauranteId,usuarioId);
    }


    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long restauranteId, @PathVariable Long usuarioId){
        cadastroRestaurante.removerResponsavel(restauranteId,usuarioId);
    }

}


