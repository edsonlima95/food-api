package com.edson.foodapi.api.controller;


import com.edson.foodapi.domain.model.Cozinha;
import com.edson.foodapi.domain.model.Restaurante;
import com.edson.foodapi.domain.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> listar() {
        return this.cozinhaService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody Cozinha cozinha) {
        this.cozinhaService.cadastrar(cozinha);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscarPorId(@PathVariable Long id) {

        Cozinha cozinha = this.cozinhaService.buscarPorId(id);

        return ResponseEntity.ok(cozinha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinha, @PathVariable Long id) {

        this.cozinhaService.buscarPorId(id);

        cozinha.setId(id);

        return ResponseEntity.ok(this.cozinhaService.atualizar(cozinha));
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
