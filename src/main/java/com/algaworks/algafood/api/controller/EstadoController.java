package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }

    @GetMapping("{id}")
    public Estado buscar(@PathVariable("id") Long Id){
        return cadastroEstado.buscarOuFalhar(Id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado salvar(@RequestBody @Valid Estado estado){
        return cadastroEstado.salvar(estado);

    }

    @DeleteMapping("{id}")

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable("id") Long estadoId){

        cadastroEstado.excluir(estadoId);

    }


    @PutMapping("{id}")
    public Estado  atualizar(@PathVariable("id") Long estadoId, @RequestBody @Valid  Estado estado){
        Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
        BeanUtils.copyProperties(estado,estadoAtual,"id");
        return  cadastroEstado.salvar(estadoAtual);
    }



}
