package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return cidadeRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable("id") Long Id){
        Optional<Cidade> cidade = cidadeRepository.findById(Id);
        if (!cidade.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cidade.get());

    }

    @PostMapping
    public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade){
        // Preciso ver se esse estado que vem do json está persistido
        Optional<Estado> estadoAtual = estadoRepository.findById(cidade.getEstado().getId());
        if (!estadoAtual.isPresent()){
            return ResponseEntity.badRequest().build();
        }

        cidade.setEstado(estadoAtual.get());
        return ResponseEntity.ok(cadastroCidade.adicionar(cidade));
    }

   @PutMapping("{id}")
    public ResponseEntity<Cidade> atualizar(@PathVariable("id") Long id,@RequestBody Cidade cidade){

        Long estadoId = cidade.getEstado().getId();
        Optional<Estado> estadoSalvo = estadoRepository.findById(estadoId);

        if (estadoSalvo.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Optional<Cidade> cidadeAtual = cidadeRepository.findById(id);
        if (cidadeAtual.isEmpty()){
            return ResponseEntity.notFound().build();
        }


        BeanUtils.copyProperties(cidade,cidadeAtual.get(),"id");
        // PRECISO PEGAR O QUE É PERSISITIDO
        cidade.setEstado(estadoSalvo.get());


        return ResponseEntity.ok(cadastroCidade.adicionar(cidade));

    }





    @DeleteMapping("{id}")
    public ResponseEntity<?> apagar(@PathVariable("id") Long Id) {
        try {
            cadastroCidade.excluir(Id);
            return ResponseEntity.noContent().build();
        }
        catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }


}
