package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.FormaPagamentoInputDissambler;
import com.algaworks.algafood.api.v1.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/formasPagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private FormaPagamentoService cadastroFormaPagamento;

    @Autowired
    private FormaPagamentoInputDissambler formaPagamentoInputDissambler;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @GetMapping
    public List<FormaPagamentoModel>  listar(){
        List<FormaPagamento> formasPagamentos = formaPagamentoRepository.findAll();


        List<FormaPagamentoModel> listaFormaPagamento = new ArrayList<FormaPagamentoModel>();

        for(FormaPagamento formaPagamento : formasPagamentos ){
            listaFormaPagamento.add(formaPagamentoModelAssembler.toModel(formaPagamento));
        }
        return listaFormaPagamento;
    }


    @GetMapping("/{id}")
    public FormaPagamentoModel get(@PathVariable Long id){

        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(id);

        return formaPagamentoModelAssembler.toModel(formaPagamento);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput){
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento = formaPagamentoInputDissambler.domainToObject(formaPagamentoInput);
        FormaPagamento formaPagamentoSalvo = cadastroFormaPagamento.salvar(formaPagamento);
        return formaPagamentoModelAssembler.toModel(formaPagamentoSalvo);
    }



    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FormaPagamentoModel atualizar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput,@PathVariable("id") Long id){
        FormaPagamento formaPagamentoSalvo = cadastroFormaPagamento.buscarOuFalhar(id);
        formaPagamentoInputDissambler.copyDomainToObject(formaPagamentoInput,formaPagamentoSalvo);
        return formaPagamentoModelAssembler.toModel(formaPagamentoSalvo);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        cadastroFormaPagamento.excluir(id);

    }






}
