package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.assembler.GrupoPermissoesDTOAssembler;
import com.edson.foodapi.api.assembler.PermissoesDTOAssembler;
import com.edson.foodapi.api.model.Inputs.GrupoPermissoesInput;
import com.edson.foodapi.api.model.dto.PermissoesDTO;
import com.edson.foodapi.domain.model.Grupo;
import com.edson.foodapi.domain.model.Permissao;
import com.edson.foodapi.domain.service.GrupoService;
import com.edson.foodapi.domain.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissoesController {


    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissoesDTOAssembler permissoesDTOAssembler;

    @Autowired
    private PermissaoService permissaoService;

    @Autowired
    private GrupoPermissoesDTOAssembler grupoPermissoesDTOAssembler;

    @GetMapping
    public List<PermissoesDTO> listar(@PathVariable Long grupoId){

        Grupo grupo = this.grupoService.buscarPorId(grupoId);

        return this.permissoesDTOAssembler.toListDTO(grupo.getPermissoes());
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionar(@PathVariable Long grupoId, @PathVariable Long permissaoId){

        Grupo grupo = this.grupoService.buscarPorId(grupoId);

        Permissao permissao = this.permissaoService.buscarPorId(permissaoId);

        grupo.getPermissoes().add(permissao);

        this.grupoService.atualizar(grupo);

    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId, @PathVariable Long permissaoId){

        Grupo grupo = this.grupoService.buscarPorId(grupoId);

        Permissao permissao = this.permissaoService.buscarPorId(permissaoId);

        grupo.getPermissoes().remove(permissao);

        this.grupoService.atualizar(grupo);

    }

    @PutMapping
    public void adicionarMuitasPermissoes(@RequestBody GrupoPermissoesInput grupoPermissoesInput, @PathVariable Long grupoId){

        Grupo grupoAtual = this.grupoService.buscarPorId(grupoId);

        this.grupoPermissoesDTOAssembler.toDomainObject(grupoPermissoesInput, grupoAtual);

        this.grupoService.adicionaMuitasPermissoes(grupoAtual);
    }

}
