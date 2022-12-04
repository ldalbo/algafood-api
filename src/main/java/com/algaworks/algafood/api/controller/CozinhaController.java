package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
// AQUI FICA O DIRETÓRIO RAIZ O QUE VIRIA DEPOIS DO "localhost:8080"
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    CozinhaRepository cozinhaRepository;

    @Autowired
    CadastroCozinhaService cadastroCozinha;

    // ACRESCENTA NO FINAL DA PRINCIPAL, NESSE CASO NADA

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar(){
       List<Cozinha> cozinhas =  cozinhaRepository.todas();
       return ResponseEntity.ok(cozinhas);
   }

    /*
    @GetMapping
    public ResponseEntity<String> teste(){

        String a = "23";
        return ResponseEntity.ok(a);
    }
    */



    @GetMapping("{id}") //AQUI PODE SER QUALQUER NOME
    public ResponseEntity<Cozinha> buscar(@PathVariable("id") Long cozinhaId){ // explicito é dizer "Pegue do Mapping o "id"
        Cozinha cozinha =  cozinhaRepository.porId(cozinhaId);
        if (cozinha != null){
            // return ResponseEntity.status(HttpStatus.OK).body(cozinha);
            return  ResponseEntity.ok(cozinha);
        }
        else{
            return  ResponseEntity.notFound().build();
        }
    }
//
//   @GetMapping("{cozinhaId}") //AQUI PODE SER QUALQUER NOME
//    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId){
//        Cozinha cozinha =  cozinhaRepository.porId(cozinhaId);
//        if (cozinha != null){
//            // return ResponseEntity.status(HttpStatus.OK).body(cozinha);
//            return  ResponseEntity.ok(cozinha);
//        }
//        else{
//            return  ResponseEntity.notFound().build();
//        }
//    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // NÃO USO O  ResponseEntity.ok(cozinha); POIS FORÇO 0 201
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
       return cadastroCozinha.salvar(cozinha);

    }

    @PutMapping("{id}") // PARA PUT pode ser os 2 "/{id}  ou "{id}"
    public ResponseEntity<Cozinha> atualizar(@PathVariable("id") Long cozinhaId, @RequestBody Cozinha cozinha){

       // PEGO DO REPOSITORIO
       Cozinha cozinhaAtual = cozinhaRepository.porId(cozinhaId);

       // VEJO SE EXISTE
       if (cozinhaAtual != null){
           // Copio todos os atributos novos para o bean persistido
           BeanUtils.copyProperties(cozinha,cozinhaAtual,"id");
           // VOU PERSISTIR O NOVO BEAN
           cozinhaAtual = cozinhaRepository.adicionar(cozinhaAtual);
           // DESSA FORMA VOLTA STATUS 200 COM O BEAN
           return ResponseEntity.ok(cozinhaAtual);
       }
       // NÃO ACHOU
       return ResponseEntity.notFound().build();
    }

     @DeleteMapping("{id}")
     public ResponseEntity<Cozinha>remove(@PathVariable("id") Long cozinhaId){

        try{
            cadastroCozinha.excluir(cozinhaId);
            return ResponseEntity.noContent().build();
        }
        catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
        catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


     }



}
