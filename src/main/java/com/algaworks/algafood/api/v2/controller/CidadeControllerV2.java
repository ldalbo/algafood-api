package com.algaworks.algafood.api.v2.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;

import com.algaworks.algafood.api.v2.assembler.CidadeInputDissamblerV2;

import com.algaworks.algafood.api.v2.assembler.CidadeModelAssemblerV2;

import com.algaworks.algafood.api.v2.model.CidadeModelV2;

import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v2/cidades")

public class CidadeControllerV2 {

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;


    @Autowired
    CadastroCidadeService cadastroCidade;

    @Autowired
    CadastroEstadoService cadastroEstado;

    @Autowired
    CidadeInputDissamblerV2 cidadeInputDissambler;

    @Autowired
    CidadeModelAssemblerV2 cidadeModelAssembler;



    @GetMapping
    public List<CidadeModelV2> listar(){
        List<Cidade> cidades = cidadeRepository.findAll();
        return cidadeModelAssembler.toCollectionModel(cidades);



    }


    @GetMapping("{id}")
    public CidadeModelV2 buscar(
                      @PathVariable Long cidadeId){
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);



        return cidadeModelAssembler.toModel(cidade);

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModelV2 adicionar(
                            @RequestBody @Valid CidadeInputV2 cidadeInput){
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
    public CidadeModelV2 atualizar(
                            @PathVariable Long cidadeId,
                            @RequestBody @Valid CidadeInputV2 cidadeInput){
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
