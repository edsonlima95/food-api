package com.edson.foodapi.domain.repositoryImpl;

import com.edson.foodapi.domain.infra.specs.RestauranteSpecs;
import com.edson.foodapi.domain.model.Restaurante;
import com.edson.foodapi.domain.repository.RestauranteRepository;
import com.edson.foodapi.domain.repository.RestauranteRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Lazy //Diz ao spring para injetar so quando for preciso, caso contrario entraria em loop de repositorios de restaurantes
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> buscarCompleta(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

        //Define o builder
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        //from Restaurante
        CriteriaQuery<Restaurante> criteriaQuery = builder.createQuery(Restaurante.class);

        //Restaurante
        Root<Restaurante> root = criteriaQuery.from(Restaurante.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText("nome")) {
            //liki nome = "%" :nome "%"
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }

        if (taxaInicial != null) {
            //taxaFrete >= :taxaInicial
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));
        }

        if (taxaFinal != null) {
            //taxaFrete <= :taxaInicial
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal));
        }

        criteriaQuery.where(predicates.toArray(Predicate[]::new));

        TypedQuery<Restaurante> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();

    }

    @Override
    public List<Restaurante> comFreteGratisENome(String nome) {
        return this.restauranteRepository.findAll(
                RestauranteSpecs.comFreteGratis().and(RestauranteSpecs.comBuscaPorNome(nome)));
    }


}
