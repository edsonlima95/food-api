package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.assembler.ProdutoDTOAssembler;
import com.edson.foodapi.api.model.dto.ProdutoDTO;
import com.edson.foodapi.domain.model.Produto;
import com.edson.foodapi.domain.model.Restaurante;
import com.edson.foodapi.domain.service.ProdutoService;
import com.edson.foodapi.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}")
public class RestauranteProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoDTOAssembler produtoDTOAssembler;

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping("/produtos")
    public List<ProdutoDTO> buscarPorRestauranteId(@PathVariable Long restauranteId) {

        Restaurante restaurante = this.restauranteService.buscarPorId(restauranteId);

        List<Produto> produtos = this.produtoService.buscarPorRestaurenteId(restaurante);

        return this.produtoDTOAssembler.toListDTO(produtos);
    }

    @GetMapping("/produtos/{produtoId}")
    public ProdutoDTO buscarPorRestauranteEProdutoId(@PathVariable Long restauranteId, @PathVariable Long produtoId) {

        Produto produto = this.produtoService.buscarPorRestauranteEProdutoId(restauranteId, produtoId);

        return this.produtoDTOAssembler.toDto(produto);
    }

}
