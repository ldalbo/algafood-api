package com.algaworks.algafood.domain.repository;


import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query("from Restaurante where nome like %:nome% ")
    List<Restaurante> buscaPorNome(String nome);


    @Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> buscaPorNomeCozinha(String nome, @Param("id") Long cozinha);


}
