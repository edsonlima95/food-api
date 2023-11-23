package com.edson.foodapi.api.controller;


import com.edson.foodapi.api.assembler.CidadeDTOAssembler;
import com.edson.foodapi.api.model.dto.CidadeDTO;
import com.edson.foodapi.domain.model.Cidade;
import com.edson.foodapi.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeDTOAssembler cidadeDTOAssembler;

    @GetMapping
    public List<CidadeDTO> listar() {

        List<Cidade> cidades = this.cidadeService.listar();

        return this.cidadeDTOAssembler.toListDTO(cidades);

    }

}
