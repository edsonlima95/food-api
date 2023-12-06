package com.edson.foodapi.domain.infra.specs;

import com.edson.foodapi.domain.model.Pedido;
import com.edson.foodapi.domain.filter.PedidoFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class PedidoSpecs {

    public static Specification<Pedido> comFiltros(PedidoFilter filtros) {
        return (root, query, builder) -> {

            //Verifica se a busca é do Pedido, se for faz o fetch caso contrário não faz.
            if (Pedido.class.equals(query.getResultType())) {
                root.fetch("restaurante").fetch("cozinha");
                root.fetch("cliente");
            }

            //Define uma lista de filtros predicates para uma lista de filtros.
            var predecateList = new ArrayList<Predicate>();

            //Define os filtros.

            if (filtros.getClienteId() != null) {
                predecateList.add(builder.equal(root.get("cliente").get("id"), filtros.getClienteId()));
            }

            if (filtros.getRestauranteId() != null) {
                predecateList.add(builder.equal(root.get("restaurante").get("id"), filtros.getRestauranteId()));
            }

            if (filtros.getDataCriacaoInicial() != null) {
                predecateList.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtros.getDataCriacaoInicial()));
            }

            if (filtros.getDataCriacaoFinal() != null) {
                predecateList.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtros.getDataCriacaoFinal()));
            }


            //Retorna os filtros, convertendo de List para Array.
            return builder.and(predecateList.toArray(Predicate[]::new));

        };

    }

}
