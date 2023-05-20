package com.algaworks.algafood.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Grupo {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(name="grupo_permissao", // NOME TABELA NOVA
            joinColumns =@JoinColumn (name="grupo_id"), // ID PARA O PROPRIO OBJETO
            inverseJoinColumns = @JoinColumn(name="permissao_id")) // ID DA OUTRA TABELA

    // É A OUTRA TABLEA DE ASSOCIAÇÃO
    // private List<Permissao> permissoes = new ArrayList<>();
    private Set<Permissao> permissoes = new HashSet<>();


    public boolean removerPermissao(Permissao permissao){
        return getPermissoes().remove(permissao);
    }

    public boolean adicionarPermissao(Permissao permissao){
        return getPermissoes().add(permissao);
    }




}