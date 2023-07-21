package com.algaworks.algafood.domain.repository;


import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long> {


}


/*
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

    // Coloco o left onde não é obrigatorio
    @Query("from Restaurante r join fetch r.cozinha left join fetch r.formaPagamento")
    List<Restaurante> findAll();

    @Query("from Restaurante where nome like %:nome% ")
    List<Restaurante> buscaPorNome(String nome);


    @Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> buscaPorNomeCozinha(String nome, @Param("id") Long cozinha);

}
*/

