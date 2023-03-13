package com.algaworks.algafood.api.model.mixin;

import com.algaworks.algafood.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CidadeMixin {



   @JsonIgnoreProperties(value = "nome", allowGetters = true)  // Ignora apenas quando vem json do Cidade
    private Estado estado;

}
