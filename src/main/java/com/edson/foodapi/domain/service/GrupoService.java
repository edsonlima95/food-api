package com.edson.foodapi.domain.service;


import com.edson.foodapi.api.assembler.GrupoDTOAssembler;
import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Grupo;
import com.edson.foodapi.domain.model.Permissao;
import com.edson.foodapi.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PermissaoService permissaoService;

    @Autowired
    private GrupoDTOAssembler grupoDTOAssembler;

    public List<Grupo> listar() {
        return this.grupoRepository.findAll();
    }

    public Grupo buscarPorId(Long id) {
        return this.grupoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }


    public void adicionaMuitasPermissoes(Grupo grupo) {
        this.grupoRepository.save(grupo);
    }


    public Grupo atualizar(Grupo grupo) {
        return this.grupoRepository.save(grupo);
    }
}

