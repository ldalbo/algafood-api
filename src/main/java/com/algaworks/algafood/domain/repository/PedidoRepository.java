package com.algaworks.algafood.domain.repository;


import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Produto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {


    @Query(" from Pedido p " +
            "join fetch p.cliente  " +
            "join fetch p.restaurante r " +
            "join fetch r.cozinha " +
            "left join fetch r.endereco.cidade c " +
            "left join fetch c.estado "
    )
    List<Pedido> findAll();
}
