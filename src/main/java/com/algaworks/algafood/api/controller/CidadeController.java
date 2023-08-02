package com.algaworks.algafood.api.controller;

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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
// Aqui faz link com a classe SpringFoxConfig
@Api(tags = "Cidades")
public class CidadeController {

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;


    @Autowired
    CadastroCidadeService cadastroCidade;

    @Autowired
    CadastroEstadoService cadastroEstado;



    @ApiOperation("Lista Cidades")
    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.findAll();




    }
    @ApiOperation("Busca uma cidade por ID")
    @GetMapping("{id}")
    public Cidade buscar(@ApiParam(value = "Id de uma cidade", example = "1")
                      @PathVariable Long cidadeId){
        return cadastroCidade.buscarOuFalhar(cidadeId);

    }

    @ApiOperation("Cria uma nova cidade")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@ApiParam(name="corpo", value="Representação de uma cidade")
                            @RequestBody @Valid Cidade cidade){
        // Preciso ver se esse estado que vem do json está persistido
        try{
            return cadastroCidade.salvar(cidade);
        }
        catch (EstadoNaoEncontradoException e ){
            System.out.println("Lançou exception EstadoNaoEncontradoException");
            throw new NegocioException(e.getMessage(),e);
        }
    }
    @ApiOperation("Atualiza uma cidade por ID")
   @PutMapping("{id}")
    public Cidade atualizar(@ApiParam(value = "Id de Uma cidade", example = "1")
                            @PathVariable("id") Long id,
                            @RequestBody @Valid Cidade cidade){
        Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);

        try{
            BeanUtils.copyProperties(cidade,cidadeAtual,"id");
            return cadastroCidade.salvar(cidade);
        }
        catch (EstadoNaoEncontradoException e ){
            throw new NegocioException(e.getMessage(),e);
        }

    }




    @ApiOperation("Exclui uma cidade por ID")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar( @ApiParam(value = "Id de Uma cidade", example = "1")
                        @PathVariable("id") Long Id) {
        cadastroCidade.excluir(Id);


    }


}
