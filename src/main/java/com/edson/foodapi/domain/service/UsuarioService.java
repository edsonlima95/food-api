package com.edson.foodapi.domain.service;


import com.edson.foodapi.domain.exception.BadRequestException;
import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Usuario;
import com.edson.foodapi.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrar(Usuario usuario) {

        Optional<Usuario> usuarioAtual = this.buscarPorEmail(usuario.getEmail());

        if(usuarioAtual.isPresent()){
            throw new BadRequestException("E-mail informado já está cadastrado");
        }

        this.usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuarioAtual = this.buscarPorId(usuarioId);

        if (!usuarioAtual.verificaSenha(senhaAtual)) {
            throw new BadRequestException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuarioAtual.setSenha(novaSenha);

        this.usuarioRepository.save(usuarioAtual);

    }

    public Usuario buscarPorId(Long id) {

        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não foi encontrado"));

    }

    public Optional<Usuario> buscarPorEmail(String email) {

        return this.usuarioRepository.findByEmail(email);

    }
}
