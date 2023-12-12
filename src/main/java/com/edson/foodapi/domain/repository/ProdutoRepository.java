package com.edson.foodapi.domain.repository;

import com.edson.foodapi.domain.model.FotoProduto;
import com.edson.foodapi.domain.model.Produto;
import com.edson.foodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    @Query("from Produto where restaurante.id = :restaurante and id = :produto")
    Optional<Produto> findByRestaurenteId(@Param("restaurante") Long restauranteId,
                               @Param("produto") Long produtoId);

    List<Produto> findByRestaurante(Restaurante restaurante);

    @Query("from Produto p where p.restaurante = :restaurante and p.ativo = true")
    List<Produto> findAtivosByRestaurante(Restaurante restaurante);


}
