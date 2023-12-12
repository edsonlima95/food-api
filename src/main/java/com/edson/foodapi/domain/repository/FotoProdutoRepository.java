package com.edson.foodapi.domain.repository;

import com.edson.foodapi.domain.model.FotoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FotoProdutoRepository extends JpaRepository<FotoProduto, Long> {

    @Query("select f from FotoProduto f join f.produto p where p.restaurante.id = :restauranteId and f.produto.id = :produtoId")
    Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);

}
