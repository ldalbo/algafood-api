package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
// AQUI FICA O DIRETÓRIO RAIZ O QUE VIRIA DEPOIS DO "localhost:8080"
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    CozinhaRepository cozinhaRepository;

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

    /* PRIMEIRA  FORMA EXPLICITANDO
    @GetMapping("/{cozinhaId}") //AQUI PODE SER QUALQUER NOME
    public Cozinha buscar(@PathVariable("cozinhaId") Long id){ // AQUI CASO COM O ATRIBUTO QUE VEM DE CIMA
        return  cozinhaRepository.porId(id);
    }

     */

   @GetMapping("{cozinhaId}") //AQUI PODE SER QUALQUER NOME
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId){
        Cozinha cozinha =  cozinhaRepository.porId(cozinhaId);

        // return ResponseEntity.status(HttpStatus.OK).body(cozinha);
        return  ResponseEntity.ok(cozinha);

    }








}
