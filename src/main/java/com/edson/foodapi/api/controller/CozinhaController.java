package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.controller.openapidoc.CozinhaControllerOpenApiDoc;
import com.edson.foodapi.domain.model.Cozinha;
import com.edson.foodapi.domain.service.CozinhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController implements CozinhaControllerOpenApiDoc {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> listar() {
        return this.cozinhaService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@Valid @RequestBody Cozinha cozinha) {
        this.cozinhaService.cadastrar(cozinha);
    }

    @GetMapping("/{id}")
    public Cozinha buscarPorId(@PathVariable Long id) {
        return this.cozinhaService.buscarPorId(id);

    }

    @PutMapping("/{id}")
    public Cozinha atualizar(@Valid @RequestBody Cozinha cozinha,
                             @PathVariable Long id) {
        return this.cozinhaService.atualizar(id, cozinha);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        this.cozinhaService.deletar(id);
    }

    @GetMapping("/por-nome")
    public ResponseEntity<List<Cozinha>> buscarPorNome(String nome) {

        List<Cozinha> cozinhas = this.cozinhaService.buscarPorNome(nome);

        return ResponseEntity.ok(cozinhas);

    }
}
