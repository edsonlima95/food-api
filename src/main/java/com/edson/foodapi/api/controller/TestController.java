package com.edson.foodapi.api.controller;


import com.edson.foodapi.domain.model.Grupo;
import com.edson.foodapi.domain.model.Permissao;
import com.edson.foodapi.domain.model.Usuario;
import com.edson.foodapi.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teste")
public class TestController {

    @Autowired
    private UsuarioService usuarioService;

   /* @GetMapping
    List<Permissao> index() {

        Usuario usuario = this.usuarioService.buscarPorId(2L);


        //return permissions;
    }*/

}
