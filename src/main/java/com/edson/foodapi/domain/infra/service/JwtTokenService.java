package com.edson.foodapi.domain.infra.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.edson.foodapi.api.model.dto.TokenDTO;
import com.edson.foodapi.domain.exception.UnauthorizedException;
import com.edson.foodapi.domain.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class JwtTokenService {

    @Value("${security.foodapi.secretkey}")
    private String secrectKey;

    @Autowired
    private UsuarioService usuarioService;

    public TokenDTO obterToken(String email, List<String> roles) {

        var validadeDoToken = gerarValidadeDoToken(1);
        var validadeDoRefreshToken = gerarValidadeDoToken(2);
        var dataAtual = LocalDateTime.now();

        var token = gerarTokenDeAcesso(email, roles, dataAtual, validadeDoToken);
        var refershToken = gerarRefreshToken(email, roles, validadeDoRefreshToken);

        return TokenDTO.builder()
                .usuario(email)
                .autenticado(true)
                .dataExpiracao(dataAtual.plusHours(1))
                .dataCriacao(dataAtual)
                .token(token)
                .refreshToken(refershToken)
                .build();
    }

    private String gerarTokenDeAcesso(String email, List<String> roles, LocalDateTime dataAtual, Instant validadeDoToken) {
        return JWT.create()
                .withIssuer("food-api")
                .withSubject(email)
                .withIssuedAt(dataAtual.toInstant(ZoneOffset.of("-03:00")))
                .withClaim("roles", roles)
                .withExpiresAt(validadeDoToken)
                .sign(Algorithm.HMAC256(secrectKey));
    }

    private String gerarRefreshToken(String email, List<String> roles, Instant validadeDoRefreshToken) {
        return JWT.create()
                .withIssuer("food-api")
                .withSubject(email)
                .withClaim("roles", roles)
                .withExpiresAt(validadeDoRefreshToken)
                .sign(Algorithm.HMAC256(secrectKey));
    }

    private Instant gerarValidadeDoToken(int horas) {
        return LocalDateTime.now().plusHours(horas).toInstant(ZoneOffset.of("-03:00"));
    }

    public Authentication autenticacao(String token) {

        DecodedJWT tokenDecodificado = this.validaToken(token);

        UserDetails usuarioPrincipal = this.usuarioService.buscarPorEmail(tokenDecodificado.getSubject());

        return new UsernamePasswordAuthenticationToken(usuarioPrincipal,
                "", usuarioPrincipal.getAuthorities());

    }

    public String obterTokenDoCabecalho(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            return token.substring("Bearer ".length());
        }

        return null;
    }

    private DecodedJWT validaToken(String token) {
            return JWT.require(Algorithm.HMAC256(secrectKey)).build().verify(token);
    }
}
