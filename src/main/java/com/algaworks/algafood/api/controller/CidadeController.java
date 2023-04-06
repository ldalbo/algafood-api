package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Cidade adicionar(@RequestBody @Valid Cidade cidade){
        // Preciso ver se esse estado que vem do json está persistido
        try{
            return cadastroCidade.salvar(cidade);
        }
        catch (EstadoNaoEncontradoException e ){
            System.out.println("Lançou exception EstadoNaoEncontradoException");
            throw new NegocioException(e.getMessage(),e);
        }
    }

   @PutMapping("{id}")
    public Cidade atualizar(@PathVariable("id") Long id,@RequestBody @Valid Cidade cidade){
        Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);

        try{
            BeanUtils.copyProperties(cidade,cidadeAtual,"id");
            return cadastroCidade.salvar(cidade);
        }
        catch (EstadoNaoEncontradoException e ){
            throw new NegocioException(e.getMessage(),e);
        }

    }





    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable("id") Long Id) {
        cadastroCidade.excluir(Id);


    }


}
