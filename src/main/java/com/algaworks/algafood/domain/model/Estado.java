package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Groups.EstadoId.class)
    private Long id;

    @NotBlank
    @Column(name="nome")
    private String nome;
}

