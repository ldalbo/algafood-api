package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
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

    @Autowired
    CadastroEstadoService cadastroEstado;



    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.findAll();
    }

    @GetMapping("{id}")
    public Cidade buscar(@PathVariable("id") Long Id){
        return cadastroCidade.buscarOuFalhar(Id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade){
        // Preciso ver se esse estado que vem do json está persistido
        return cadastroCidade.salvar(cidade);
    }

   @PutMapping("{id}")
    public Cidade atualizar(@PathVariable("id") Long id,@RequestBody Cidade cidade){
       System.out.println(" 00 ");
        Long estadoId = cidade.getEstado().getId();
        Estado estadoSalvo = cadastroEstado.buscarOuFalhar(estadoId);

       System.out.println(" 01");

        Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);

       System.out.println(" 02 ");
        BeanUtils.copyProperties(cidade,cidadeAtual,"id");
        // PRECISO PEGAR O QUE É PERSISITIDO
        cidade.setEstado(estadoSalvo);

        return cadastroCidade.salvar(cidade);

    }





    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable("id") Long Id) {
        cadastroCidade.excluir(Id);


    }


}
