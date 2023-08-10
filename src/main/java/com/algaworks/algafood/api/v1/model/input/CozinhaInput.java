package com.algaworks.algafood.api.v1.model.input;


import com.algaworks.algafood.core.validation.Groups;
import com.algaworks.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter

public class CozinhaInput {


    @NotBlank
    private String nome;




}
