package com.edson.foodapi.domain.service;

import com.edson.foodapi.api.model.dto.TokenDTO;
import com.edson.foodapi.domain.infra.service.JwtTokenService;
import com.edson.foodapi.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioService usuarioService;

    public TokenDTO logar(Usuario usuario) {

        Usuario usuarioAtual = this.usuarioService.buscarPorEmail(usuario.getEmail());

        Authentication autenticacao = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioAtual.getUsername(), usuario.getPassword()));

        return this.jwtTokenService.obterToken(usuario.getEmail(), usuario.roles());
    }

}
