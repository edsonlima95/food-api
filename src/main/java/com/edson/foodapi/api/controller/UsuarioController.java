package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.assembler.UsuarioDTOAssembler;
import com.edson.foodapi.api.model.Inputs.SenhaInput;
import com.edson.foodapi.api.model.Inputs.UsuarioInput;
import com.edson.foodapi.api.model.Inputs.UsuarioInputUpdate;
import com.edson.foodapi.api.model.dto.UsuarioDTO;
import com.edson.foodapi.domain.model.Usuario;
import com.edson.foodapi.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDTOAssembler usuarioDTOAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@Valid @RequestBody UsuarioInput usuarioInput) {

        Usuario usuario = this.usuarioDTOAssembler.toModel(usuarioInput);

        this.usuarioService.cadastrar(usuario);

    }

    @PutMapping("/{id}")
    public UsuarioDTO atualizar(@Valid @RequestBody UsuarioInputUpdate usuarioInputUpdate, @PathVariable Long id) {


        Usuario usuarioAtual = this.usuarioService.buscarPorId(id);

        this.usuarioDTOAssembler.toModelObject(usuarioInputUpdate, usuarioAtual);

        usuarioAtual = this.usuarioService.atualizar(usuarioAtual);

        return this.usuarioDTOAssembler.toDto(usuarioAtual);

    }

    @PutMapping("/{id}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarSenha(@Valid @RequestBody SenhaInput senhaInput, @PathVariable Long id) {

        this.usuarioService.alterarSenha(id, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());

    }
}
