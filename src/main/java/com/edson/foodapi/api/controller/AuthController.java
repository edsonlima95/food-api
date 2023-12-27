package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.assembler.UsuarioCredenciaisAssembler;
import com.edson.foodapi.api.model.Inputs.UsuarioCredenciaisInput;
import com.edson.foodapi.api.model.dto.TokenDTO;
import com.edson.foodapi.domain.model.Usuario;
import com.edson.foodapi.domain.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioCredenciaisAssembler usuarioCredenciaisAssembler;

    @PostMapping("/login")
    public TokenDTO login(@Valid @RequestBody UsuarioCredenciaisInput usuarioCredenciaisInput) {

        Usuario usuario = this.usuarioCredenciaisAssembler.toDomainModel(usuarioCredenciaisInput);

        return this.authService.logar(usuario);

    }

}
