package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Restaurante> todos() {
        return manager.createQuery("from Restaurante",Restaurante.class)
                .getResultList();
    }

    @Override
    public Restaurante porId(Long id) {
        return manager.find(Restaurante.class,id);
    }

    @Override
    @Transactional
    public Restaurante adicionar(Restaurante restaurante) {
        return manager.merge(restaurante);
    }

    @Override
    @Transactional
    public void remover(Restaurante restaurante) {
        restaurante = porId(restaurante.getId());
        manager.remove(restaurante);

    }
}
