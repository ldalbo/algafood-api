package com.algaworks.algafood.domain.repository;


import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long> {


    class CustomJpaRepositoryImpl<T,ID> extends SimpleJpaRepository<T,ID>
            implements CustomJpaRepository<T, ID> {

        private EntityManager manager;

        public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
            super(entityInformation, entityManager);
            this.manager = entityManager;
        }

        @Override
        public Optional<T> buscarPrimeiro() {
            var jpql = "from " + getDomainClass().getCanonicalName();
            T entity = manager.createQuery(jpql, getDomainClass())
                    .setMaxResults(1)
                    .getSingleResult();

            return Optional.ofNullable(entity);
        }

        @Override
        public void detach(T entity){
            manager.detach(entity);
        }
    }
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

