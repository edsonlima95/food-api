package com.edson.foodapi.api.controller;


import com.edson.foodapi.domain.model.Cozinha;
import com.edson.foodapi.domain.model.Estado;
import com.edson.foodapi.domain.service.CozinhaService;
import com.edson.foodapi.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> listar() {
        return this.estadoService.listar();
    }

}
