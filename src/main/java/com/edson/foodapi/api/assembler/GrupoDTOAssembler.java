package com.edson.foodapi.api.assembler;

import com.edson.foodapi.api.model.Inputs.GrupoInput;
import com.edson.foodapi.api.model.dto.GrupoDTO;
import com.edson.foodapi.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public Grupo toModel(GrupoInput grupoInput) {
        return this.modelMapper.map(grupoInput, Grupo.class);
    }

    public void toDomainObject(GrupoInput grupoInput, Grupo grupo) {
         this.modelMapper.map(grupoInput, grupo);
    }

    public GrupoDTO toDto(Grupo grupo) {
        return this.modelMapper.map(grupo, GrupoDTO.class);
    }

    public List<GrupoDTO> toListDTO(List<Grupo> grupos) {
        return grupos.stream().map(this::toDto).collect(Collectors.toList());
    }

}
