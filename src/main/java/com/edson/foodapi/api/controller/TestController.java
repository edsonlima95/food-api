package com.edson.foodapi.api.controller;


import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.FotoProduto;
import com.edson.foodapi.domain.repository.FotoProdutoRepository;
import com.edson.foodapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TestController {

    @Autowired
    private FotoProdutoRepository fotoProdutoRepository;

    @PutMapping("/{restauranteId}/{produtoId}")
    @Transactional
    FotoProduto index(@PathVariable Long restauranteId, @PathVariable Long produtoId) {

        FotoProduto fotoProduto =
                this.fotoProdutoRepository.findFotoById(restauranteId, produtoId)
                        .orElseThrow(() -> new NotFoundException(produtoId));

        this.fotoProdutoRepository.delete(fotoProduto);

       return fotoProduto;

    }

}
