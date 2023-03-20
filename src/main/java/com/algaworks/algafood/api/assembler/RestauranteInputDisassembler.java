package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class RestauranteInputDisassembler {

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        var restaurante = new Restaurante();

        restaurante.setId(restauranteInput.getCozinha().getId());
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

        OffsetDateTime dataCadastro = OffsetDateTime.now();
        restaurante.setDataCadastro(dataCadastro);

        OffsetDateTime dataAtualizacao = OffsetDateTime.now();
        restaurante.setDataAtualizacao(dataAtualizacao);

        var cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());
        restaurante.setCozinha(cozinha);

        return restaurante;

    }
}
