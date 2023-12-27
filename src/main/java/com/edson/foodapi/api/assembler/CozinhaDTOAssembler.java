package com.edson.foodapi.api.assembler;

import com.edson.foodapi.api.model.Inputs.CozinhaInput;
import com.edson.foodapi.api.model.dto.CozinhaDTO;
import com.edson.foodapi.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;


    public CozinhaDTO toDto(Cozinha cozinha) {
        return this.modelMapper.map(cozinha, CozinhaDTO.class);
    }

    public Cozinha toModel(CozinhaDTO cozinhaDTO) {
        return this.modelMapper.map(cozinhaDTO, Cozinha.class);
    }

    public Cozinha toDomainObject(CozinhaInput cozinhaInput) {
        return this.modelMapper.map(cozinhaInput, Cozinha.class);
    }

    public List<CozinhaDTO> toListDto(List<Cozinha> cozinhas) {
        return cozinhas.stream().map(this::toDto).collect(Collectors.toList());
    }

}
