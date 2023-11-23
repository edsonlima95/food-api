package com.edson.foodapi.domain.service;


import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Usuario;
import com.edson.foodapi.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public void cadastrar(Usuario usuario) {
        this.usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
//        usuario.setId(id);
        return this.usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {

        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não foi encontrado"));

    }
}
