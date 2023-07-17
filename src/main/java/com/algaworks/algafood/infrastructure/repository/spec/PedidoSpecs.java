package com.algaworks.algafood.infrastructure.repository.spec;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class PedidoSpecs {

	public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
		return (root, query, builder) -> {
			var predicates = new ArrayList<Predicate>();
			// Se oresultado da Pesquisa for uma query, dou um fech,
			// pois não consegue dar um count(*) encima de 1 query
			 if (Pedido.class.equals(query.getResultType())){
				// Isso é para evitar vários acessos ao banco, ai ele faz 1 load unico

				// Preciso de um fetch aninhado, pois cozinha está vinculada
				// a um restaurante
				root.fetch("restaurante").fetch("cozinha");

				root.fetch("cliente");
				// Adicionando os predicados
			}

			if (filtro.getClienteId() != null){
				predicates.add(builder.equal(root.get("cliente"),filtro.getClienteId()));
			}

			if (filtro.getRestauranteId() != null){
				predicates.add(builder.equal(root.get("restaurante"),filtro.getRestauranteId()));
			}

			if (filtro.getDataCriacaoInicio() != null){
				predicates.add((builder.greaterThan(root.get("dataCriacao"),
						filtro.getDataCriacaoInicio())));
			}

			if (filtro.getDataCriacaoFim() != null){
				predicates.add((builder.lessThanOrEqualTo(root.get("dataCriacao"),
						filtro.getDataCriacaoFim())));
			}

			return builder.and(predicates.toArray (new Predicate[0]));
		};
	}
	

	
}
