package com.algaworks.algafood.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
// @Table(name="tab_cozinhas")
public class Cozinha {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long Id;



    // @Column(name="nom_cozinha")
    private String nome;

    public long getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Cozinha cozinha = (Cozinha) o;
        return Id == cozinha.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }



}
