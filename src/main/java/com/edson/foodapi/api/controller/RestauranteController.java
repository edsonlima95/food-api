package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.assembler.RestauranteDTOAssembler;
import com.edson.foodapi.api.model.Inputs.RestauranteInput;
import com.edson.foodapi.api.model.dto.RestauranteDTO;
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
    public void cadastrar(@Valid @RequestBody RestauranteInput restauranteInput) {

        Restaurante restaurante = this.restauranteAssembler.toModel(restauranteInput);

        try {
            this.restauranteService.cadastrar(restaurante);
        } catch (NotFoundException ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public RestauranteDTO buscarPorId(@PathVariable Long id) {

        Restaurante restaurante = this.restauranteService.buscarPorId(id);

        return this.restauranteAssembler.toDto(restaurante);
    }

    @PutMapping("/{id}")
    public RestauranteDTO atualizar(@Valid @RequestBody RestauranteInput restauranteInput, @PathVariable Long id) {

        Restaurante restaurante = this.restauranteAssembler.toModel(restauranteInput);

        this.restauranteService.buscarPorId(id);

        restaurante.setId(id);

        try {

            Restaurante res = this.restauranteService.atualizar(restaurante);
            return this.restauranteAssembler.toDto(res);

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

    @PutMapping("/{id}/ativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long id){
        this.restauranteService.ativar(id);
    }

    @DeleteMapping("/{id}/inativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long id){
        this.restauranteService.inativar(id);
    }
}
