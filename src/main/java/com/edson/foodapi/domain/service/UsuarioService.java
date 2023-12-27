package com.edson.foodapi.domain.service;


import com.edson.foodapi.domain.exception.BadRequestException;
import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Usuario;
import com.edson.foodapi.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrar(Usuario usuario) {

        Usuario usuarioAtual = this.buscarPorEmail(usuario.getEmail());

        if(usuarioAtual != null){
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
                .orElseThrow(() -> new NotFoundException(id));

    }

    public Usuario buscarPorEmail(String email) {

        return this.usuarioRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("Usuário não encontrado"));

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.buscarPorEmail(email);
    }
}
