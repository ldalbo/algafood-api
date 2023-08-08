package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CidadeInputDissambler;
import com.algaworks.algafood.api.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;

import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Autowired
    CidadeInputDissambler cidadeInputDissambler;

    @Autowired
    CidadeModelAssembler cidadeModelAssembler;



    @GetMapping
    public List<CidadeModel> listar(){
        List<Cidade> cidades = cidadeRepository.findAll();
        return cidadeModelAssembler.toCollectionModel(cidades);



    }


    @GetMapping("{id}")
    public CidadeModel buscar(
                      @PathVariable Long cidadeId){
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);



        return cidadeModelAssembler.toModel(cidade);

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(
                            @RequestBody @Valid CidadeInput cidadeInput){
        // Preciso ver se esse estado que vem do json está persistido
        try{

            // Do input transformo em objeto
            Cidade cidade = new Cidade();
            cidade = cidadeInputDissambler.domainToObject(cidadeInput);
            return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidade));




        }
        catch (EstadoNaoEncontradoException e ){
            System.out.println("Lançou exception EstadoNaoEncontradoException");
            throw new NegocioException(e.getMessage(),e);
        }
    }



   @PutMapping("{id}")
    public CidadeModel atualizar(
                            @PathVariable Long cidadeId,
                            @RequestBody @Valid CidadeInput cidadeInput){
        try{
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
            cidadeInputDissambler.copyDomainToObject(cidadeInput,cidadeAtual);
            return cidadeModelAssembler.toModel(cidadeAtual);
        }
        catch (EstadoNaoEncontradoException e ){
            throw new NegocioException(e.getMessage(),e);
        }

    }




    @ApiOperation("Exclui uma cidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cidade excluida",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar( @PathVariable("id") Long Id) {
        cadastroCidade.excluir(Id);


    }


}
