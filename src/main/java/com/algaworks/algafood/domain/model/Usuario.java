package com.algaworks.algafood.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @CreatedDate
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @ManyToMany
    @JoinTable(name="usuario_grupo",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name="grupo_id"))
  List<Grupo> grupos = new ArrayList<>();

}