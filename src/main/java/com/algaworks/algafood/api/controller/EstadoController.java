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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    CadastroEstadoService cadastroEstado;



    @GetMapping
    public ResponseEntity<List<Estado>> listar(){
        List<Estado> estados =  estadoRepository.todos();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("{id}")
    public ResponseEntity buscar(@PathVariable("id") Long Id){
        Estado estado = estadoRepository.porId(Id);
        if (estado == null){
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado salvar(@RequestBody  Estado estado){
        return cadastroEstado.salvar(estado);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remover (@PathVariable("id") Long estadoId){
       try{
           cadastroEstado.excluir(estadoId);
           return ResponseEntity.noContent().build();
       }
       catch (EntidadeEmUsoException e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
        catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@PathVariable("id") Long estadoId, @RequestBody Estado estado){
        Estado estadoAtual = estadoRepository.porId(estadoId);
        if (estadoAtual == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado " + estadoId +  " não encontrado");

        }
        // ATRIBUTO JSON A SER IGNORADO
        BeanUtils.copyProperties(estado,estadoAtual,"id");
        // OU
        // estadoAtual.setNome(estado.getNome());
        estadoAtual = cadastroEstado.salvar(estadoAtual);
        return ResponseEntity.ok(estadoAtual);
    }



}
