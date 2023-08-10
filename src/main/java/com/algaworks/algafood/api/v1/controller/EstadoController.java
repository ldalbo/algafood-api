package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/estados")
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
        System.out.println("EstadoCrontroller.salvar");
        try{
            return cadastroEstado.salvar(estado);
        }
        catch (EstadoNaoEncontradoException e ){
            throw new NegocioException(e.getMessage(),e);
        }

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
