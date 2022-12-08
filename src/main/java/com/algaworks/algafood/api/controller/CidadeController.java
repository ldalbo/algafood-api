package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;


    @Autowired
    CadastroCidadeService cadastroCidade;



    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.todos();
    }

    @GetMapping("{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable("id") Long Id){
        Cidade cidade = cidadeRepository.porId(Id);
        if (cidade == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cidade);

    }

    @PostMapping
    public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade){
        // Preciso ver se esse estado que vem do json está persistido
        Long estadoId = cidade.getEstado().getId();
        Estado estadoSalvo = estadoRepository.porId(estadoId);

        if (estadoSalvo == null){
           return ResponseEntity.badRequest().build();
        }
        cidade.setEstado(estadoSalvo);
        return ResponseEntity.ok(cadastroCidade.adicionar(cidade));
    }

    @PutMapping("{id}")
    public ResponseEntity<Cidade> atualizar(@PathVariable("id") Long id,@RequestBody Cidade cidade){

        Long estadoId = cidade.getEstado().getId();
        Estado estadoSalvo = estadoRepository.porId(estadoId);

        if (estadoSalvo == null){
            return ResponseEntity.badRequest().build();
        }
        Cidade cidadeAtual = cidadeRepository.porId(id);
        cidadeAtual.setEstado(estadoSalvo);
        cidadeAtual.setNome(cidade.getNome());
        return ResponseEntity.ok(cadastroCidade.adicionar(cidade));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> apagar(@PathVariable("id") Long Id) {
        try {
            System.out.println("Controller " + Id);
            cadastroCidade.excluir(Id);
            return ResponseEntity.noContent().build();
        }
        catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }


}
