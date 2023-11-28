package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.assembler.GrupoDTOAssembler;
import com.edson.foodapi.api.assembler.UsuarioDTOAssembler;
import com.edson.foodapi.api.model.dto.GrupoDTO;
import com.edson.foodapi.api.model.dto.UsuarioDTO;
import com.edson.foodapi.domain.model.Grupo;
import com.edson.foodapi.domain.model.Usuario;
import com.edson.foodapi.domain.repository.UsuarioRepository;
import com.edson.foodapi.domain.service.GrupoService;
import com.edson.foodapi.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGruposController {


    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GrupoDTOAssembler grupoDTOAssembler;

    @Autowired
    private GrupoService grupoService;


    @GetMapping
    public List<GrupoDTO> listar(@PathVariable Long usuarioId){

        Usuario usuario = this.usuarioService.buscarPorId(usuarioId);

       return this.grupoDTOAssembler.toListDTO(usuario.getGrupos());
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionar(@PathVariable Long grupoId, @PathVariable Long usuarioId) {

        Usuario usuario = this.usuarioService.buscarPorId(usuarioId);

        Grupo grupo = this.grupoService.buscarPorId(grupoId);

        usuario.getGrupos().add(grupo);

        this.usuarioService.atualizar(usuario);

    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId, @PathVariable Long usuarioId) {

        Usuario usuario = this.usuarioService.buscarPorId(usuarioId);

        Grupo grupo = this.grupoService.buscarPorId(grupoId);

        usuario.getGrupos().remove(grupo);

        this.usuarioService.atualizar(usuario);

    }

}
