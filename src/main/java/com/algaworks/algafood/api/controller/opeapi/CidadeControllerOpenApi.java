package com.algaworks.algafood.api.controller.opeapi;


import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



import java.util.List;


@Api(tags = "Cidades")
public interface  CidadeControllerOpenApi {


    @ApiOperation("Lista Cidades")

    public List<CidadeModel> listar();


    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })


    public CidadeModel buscar(@ApiParam(value = "Id de uma cidade", example = "1")
                      Long cidadeId);

    @ApiOperation("Cria uma nova cidade")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cidade Criada com Sucesso",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })

    public CidadeModel adicionar(@ApiParam(name="corpo", value="Representação de uma cidade")
                            CidadeInput cidadeInput);


    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cidade atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })

    public CidadeModel atualizar(@ApiParam(value = "Id de Uma cidade", example = "1")
                             Long cidadeId,
                            CidadeInput cidadeInput);




    @ApiOperation("Exclui uma cidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cidade excluida",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada",
                    content = @Content(schema = @Schema(implementation = Problem.class)))
    })



    public void apagar( @ApiParam(value = "Id de Uma cidade", example = "1")
                         Long Id) ;



}
