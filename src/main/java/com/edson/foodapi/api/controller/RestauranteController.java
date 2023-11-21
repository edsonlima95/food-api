package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.assembler.RestauranteDTOAssembler;
import com.edson.foodapi.api.model.RestauranteDTO;
import com.edson.foodapi.domain.exception.BadRequestException;
import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Restaurante;
import com.edson.foodapi.domain.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    RestauranteDTOAssembler restauranteAssembler;

    @GetMapping
    public List<RestauranteDTO> listar() {
        List<Restaurante> restauranteList = this.restauranteService.listar();
        return restauranteAssembler.toListDTO(restauranteList);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@Valid @RequestBody Restaurante restaurante) {
        try {
            this.restauranteService.cadastrar(restaurante);
        } catch (NotFoundException ex) {
            throw new BadRequestException("Ocorreu um erro inesperado, verifique os dados e envie novamente");
        }
    }

    @GetMapping("/{id}")
    public RestauranteDTO buscarPorId(@PathVariable Long id) {

        Restaurante restaurante = this.restauranteService.buscarPorId(id);

        return this.restauranteAssembler.toDto(restaurante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@Valid @RequestBody Restaurante restaurante, @PathVariable Long id) {

        this.buscarPorId(id);

        restaurante.setId(id);

        try {
            return ResponseEntity.ok(this.restauranteService.atualizar(restaurante));
        } catch (NotFoundException ex) {
            throw new BadRequestException("Ocorreu um erro inesperado, verifique os dados e envie novamente");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        this.restauranteService.deletar(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Restaurante>> buscaCompleta(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

        List<Restaurante> restaurantes = this.restauranteService.buscaCompleta(nome, taxaInicial, taxaFinal);

        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/buscar-frete-gratis")
    public ResponseEntity<List<Restaurante>> buscaComFreteGratisEnome(String nome) {

        List<Restaurante> restaurantes = this.restauranteService.buscaComFreteGratis(nome);

        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/buscar-primeiro")
    public Optional<Restaurante> buscaPrimeiro() {
        return this.restauranteService.buscaPrimeiro();
    }
}
