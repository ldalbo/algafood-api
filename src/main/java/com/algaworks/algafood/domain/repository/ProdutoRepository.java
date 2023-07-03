package com.algaworks.algafood.domain.repository;


import com.algaworks.algafood.domain.model.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends CustomJpaRepository<Produto, Long> {

    Optional<Produto> findByIdAndRestauranteId(Long id,Long restaurante);

    @Query("from Produto where restaurante.id = :restaurante and id = :produto")
    Optional<Produto> findById(@Param("restaurante") Long restauranteId,
                               @Param("produto") Long produtoId);


    @Query("from Produto where restaurante.id = :restaurante ")
    List<Produto> findByRestaurante(@Param("restaurante") Long restauranteId);


    //  @Query("from Produto where restaurante.id = :restaurante and ativo = 1")
    // Esta é a forma nativa de consultar
     @Query(value = "SELECT p.id , p.ativo, p.nome, p.descricao, " +
                           " p.preco, p.restaurante_id " +
                   " FROM produto p " +
                   " WHERE restaurante_id = :restaurante AND ativo = 1 ",nativeQuery = true)


    List<Produto> findAtivosByRestaurante(@Param("restaurante") Long restauranteId);




}
