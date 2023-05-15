package com.algaworks.algafood.domain.repository;


import com.algaworks.algafood.domain.model.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProdutoRepository extends CustomJpaRepository<Produto, Long> {
    Optional<Produto> findByIdAndRestauranteId(Long id,Long restaurante);

    @Query("from Produto where restaurante.id = :restaurante and id = :produto")
    Optional<Produto> findById(@Param("restaurante") Long restauranteId,
                               @Param("produto") Long produtoId);
}
