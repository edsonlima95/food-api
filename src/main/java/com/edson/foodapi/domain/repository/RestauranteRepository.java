package com.edson.foodapi.domain.repository;

import com.edson.foodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
        RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

}
