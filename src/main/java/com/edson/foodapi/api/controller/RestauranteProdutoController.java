package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.assembler.ProdutoDTOAssembler;
import com.edson.foodapi.api.model.Inputs.FotoProdutoInput;
import com.edson.foodapi.api.model.dto.ProdutoDTO;
import com.edson.foodapi.domain.model.Produto;
import com.edson.foodapi.domain.model.Restaurante;
import com.edson.foodapi.domain.service.ProdutoService;
import com.edson.foodapi.domain.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

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
    public List<ProdutoDTO> buscarPorRestauranteId(@PathVariable Long restauranteId,
                                                   @RequestParam(required = false) boolean ativo) {

        Restaurante restaurante = this.restauranteService.buscarPorId(restauranteId);

        List<Produto> produtos = restaurante.getProdutos();

        if (ativo) {
            produtos = this.produtoService.buscarPorAtivos(restaurante);
        }

        return this.produtoDTOAssembler.toListDTO(produtos);
    }

    @GetMapping("/produtos/{produtoId}")
    public ProdutoDTO buscarPorRestauranteEProdutoId(@PathVariable Long restauranteId,
                                                     @PathVariable Long produtoId) {

        Produto produto = this.produtoService.buscarPorRestauranteEProdutoId(restauranteId, produtoId);

        return this.produtoDTOAssembler.toDto(produto);
    }

    @PutMapping(value = "/produtos/{produtoId}/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void alteraFotoProduto(@PathVariable Long restauranteId,
                                  @PathVariable Long produtoId,
                                  @Valid FotoProdutoInput fotoProdutoInput) {

        this.produtoService.buscarPorRestauranteEProdutoId(restauranteId, produtoId);

        var nomeArquivo = UUID.randomUUID().toString() + "-" + fotoProdutoInput.getArquivo().getOriginalFilename();

        var caminho = Path.of("D:/projetos/java/food-api/uploads",nomeArquivo);

        try {
            fotoProdutoInput.getArquivo().transferTo(caminho);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
