package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {


    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="nome")
    private String nome;

    // SE NÃO FAÇO VOLTAR, O BEAN FICA NULO, ENTÃO DEVEMOS CUIDAR
    // @JsonIgnore
    @Valid
    @NotNull
    @ManyToOne
  //  @ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
    @JoinColumn(name = "estado_id")
    private Estado estado;

}
