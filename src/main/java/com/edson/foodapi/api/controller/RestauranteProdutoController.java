package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.assembler.FotoProdutoDTOAssembler;
import com.edson.foodapi.api.assembler.ProdutoDTOAssembler;
import com.edson.foodapi.api.model.Inputs.FotoProdutoInput;
import com.edson.foodapi.api.model.dto.FotoProdutoDTO;
import com.edson.foodapi.api.model.dto.ProdutoDTO;
import com.edson.foodapi.domain.infra.service.LocalStorageService;
import com.edson.foodapi.domain.infra.service.S3StorageService;
import com.edson.foodapi.domain.model.FotoProduto;
import com.edson.foodapi.domain.model.Produto;
import com.edson.foodapi.domain.model.Restaurante;
import com.edson.foodapi.domain.service.FotoProdutoService;
import com.edson.foodapi.domain.service.FotoStorageService;
import com.edson.foodapi.domain.service.ProdutoService;
import com.edson.foodapi.domain.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.NotBoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}")
public class RestauranteProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FotoProdutoService fotoProdutoService;

    @Autowired
    private RestauranteService restauranteService;

/*    @Autowired
    private LocalStorageService localStorageService;

    @Autowired
    private S3StorageService s3StorageService;*/

    private FotoStorageService fotoStorage;

    @Autowired
    private ProdutoDTOAssembler produtoDTOAssembler;

    @Autowired
    private FotoProdutoDTOAssembler fotoProdutoDTOAssembler;

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
    public ProdutoDTO buscarProdutos(@PathVariable Long restauranteId,
                                     @PathVariable Long produtoId) {

        Produto produto = this.produtoService.buscarPorRestauranteEProdutoId(restauranteId, produtoId);

        return this.produtoDTOAssembler.toDto(produto);
    }

    @PutMapping(value = "/produtos/{produtoId}/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void alteraFotoProduto(@PathVariable Long restauranteId,
                                  @PathVariable Long produtoId,
                                  @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

        Produto produto = this.produtoService.buscarPorRestauranteEProdutoId(restauranteId, produtoId);

        FotoProduto fotoProduto = new FotoProduto();
        fotoProduto.setProduto(produto);
        fotoProduto.setDescricao(fotoProdutoInput.getDescricao());
        fotoProduto.setNomeArquivo(fotoProdutoInput.getArquivo().getOriginalFilename());
        fotoProduto.setContentType(fotoProdutoInput.getArquivo().getContentType());
        fotoProduto.setTamanho(fotoProdutoInput.getArquivo().getSize());

        this.fotoProdutoService.atualizar(fotoProduto, fotoProdutoInput.getArquivo().getInputStream());

    }

    @GetMapping(value = "/produtos/{produtoId}/foto", produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoDTO buscarFotoDoProduto(@PathVariable Long restauranteId,
                                              @PathVariable Long produtoId) {

        FotoProduto fotoProduto = this.fotoProdutoService.buscarPorResutauranteEProdutoId(restauranteId, produtoId);

        return this.fotoProdutoDTOAssembler.toDto(fotoProduto);
    }

    @GetMapping(value = "/produtos/{produtoId}/foto", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> servirFotoDoProduto(@PathVariable Long restauranteId,
                                                                   @PathVariable Long produtoId) {
        try {

            FotoProduto fotoProduto = this.fotoProdutoService.buscarPorResutauranteEProdutoId(restauranteId, produtoId);

            FotoStorageService.RecuperarArquivo fotoRecuperada = fotoStorage.recuperar(fotoProduto.getNomeArquivo());

            if (fotoRecuperada.getUrl() != null) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(new InputStreamResource(fotoRecuperada.getInputStream()));
            }

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/produtos/{produtoId}/foto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleta(@PathVariable Long restauranteId,
                       @PathVariable Long produtoId) {

        FotoProduto fotoProduto = this.fotoProdutoService.buscarPorResutauranteEProdutoId(restauranteId, produtoId);

        this.fotoProdutoService.deletar(fotoProduto);

        //Remove foto local
        //this.localStorageService.remover(fotoProduto.getNomeArquivo());

        //Remove foto s3
        //this.s3StorageService.remover(fotoProduto.getNomeArquivo());

        this.fotoStorage.remover(fotoProduto.getNomeArquivo());
    }
}
