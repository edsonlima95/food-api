package com.edson.foodapi.api.controller;

import com.edson.foodapi.api.assembler.ProdutoDTOAssembler;
import com.edson.foodapi.api.model.Inputs.ProdutoInput;
import com.edson.foodapi.domain.exception.BadRequestException;
import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Produto;
import com.edson.foodapi.domain.service.ProdutoService;
import com.edson.foodapi.domain.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoDTOAssembler produtoDTOAssembler;

    @Autowired
    private RestauranteService restauranteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@Valid @RequestBody ProdutoInput produtoInput) {

        Produto produto = this.produtoDTOAssembler.toModel(produtoInput);

        try {
            this.produtoService.cadastrar(produto);
        } catch (NotFoundException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

}
