package com.edson.foodapi.api.assembler;


import com.edson.foodapi.api.model.dto.PermissoesDTO;
import com.edson.foodapi.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissoesDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissoesDTO toDto(Permissao permissao){
        return this.modelMapper.map(permissao, PermissoesDTO.class);
    }

    public List<PermissoesDTO> toListDTO(Collection<Permissao> permissoes){
        return permissoes.stream().map(this::toDto).collect(Collectors.toList());
    }

}
