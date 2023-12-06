package com.edson.foodapi.domain.infra.service;

import com.edson.foodapi.domain.filter.VendaDiariaFilter;
import com.edson.foodapi.domain.model.Pedido;
import com.edson.foodapi.domain.model.StatusPedido;
import com.edson.foodapi.domain.model.dto.VendaDiaria;
import com.edson.foodapi.domain.service.VendaQueryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaDiaria> consultarVendasDiaria(VendaDiariaFilter filtros) {

        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);

        var predicates = new ArrayList<Predicate>();

  /*      var dataUtc = builder.function(
                "AT TIME ZONE",
                Date.class,
                builder.literal("America/Sao_Paulo"),
                root.get("dataCriacao")
        );*/


        var functionDate = builder.function("date", Date.class, root.get("dataCriacao"));

        var selection = builder.construct(VendaDiaria.class,
                functionDate,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

        if (filtros.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante").get("id"), filtros.getRestauranteId()));
        }

        if (filtros.getDataCriacaoInicial() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtros.getDataCriacaoInicial()));
        }

        if (filtros.getDataCriacaoFinal() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtros.getDataCriacaoFinal()));
        }

        predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

        query.select(selection);
        query.where(predicates.toArray(Predicate[]::new));

        query.groupBy(functionDate);

        return manager.createQuery(query).getResultList();
    }
}
