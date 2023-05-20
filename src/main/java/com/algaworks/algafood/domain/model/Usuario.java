package com.algaworks.algafood.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="usuario") // apenas para lembrar que se não informar pega o nome da classe, usado para legados
public class Usuario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nome;

    @Column(nullable = false)
    private String email;

    // aqui apenas coloquei para lembrar que sistemas legados,
    // precisa explicitar o nome do campo na table
    @Column(name="senha", nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(name="data_cadastro", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @ManyToMany
    @JoinTable(name="usuario_grupo",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name="grupo_id"))

    private Set<Grupo> grupos = new HashSet<>();


    public boolean desassociarGrupo(Grupo grupo){
        return getGrupos().remove(grupo);
    }

    public boolean associaciarGrupo(Grupo grupo){
        return getGrupos().add(grupo);

    }



}