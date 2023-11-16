package com.edson.foodapi.api.controller;


import com.edson.foodapi.domain.model.Restaurante;
import com.edson.foodapi.domain.service.RestauranteService;
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

    @GetMapping
    public List<Restaurante> listar() {
        return this.restauranteService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody Restaurante restaurante) {

        //System.out.println(restaurante);

        this.restauranteService.cadastrar(restaurante);
    }

    @GetMapping("/{id}")
    public Restaurante buscarPorId(@PathVariable Long id) {
        return this.restauranteService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@RequestBody Restaurante restaurante, @PathVariable Long id) {
        return ResponseEntity.ok(this.restauranteService.atualizar(restaurante, id));
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
