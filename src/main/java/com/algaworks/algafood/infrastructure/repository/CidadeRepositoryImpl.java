package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    EntityManager manager;

    @Override
    public Cidade porId(Long Id) {
        return manager.find(Cidade.class, Id);
    }

    @Override
    @Transactional
    public Cidade adicionar(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Override
    @Transactional
    public void remover(Long Id) {
        System.out.println("Id que chegou no repository " + Id);
        Cidade cidade = porId(Id);
        if (cidade == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cidade);

    }

    @Override
    public List<Cidade> todos() {
        return manager.createQuery("from Cidade",Cidade.class)
                .getResultList();

        /*
        return manager.createQuery("SELECT id, nome, estado_id FROM cidade")
                .getResultList();

         */
    }
}
