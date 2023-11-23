package com.edson.foodapi.api.assembler;

import com.edson.foodapi.api.model.dto.CidadeDTO;
import com.edson.foodapi.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeDTO toDto(Cidade cidade) {
        return this.modelMapper.map(cidade, CidadeDTO.class);
    }

    /*public Cidade toModel(RestauranteInput restauranteInput) {
        return this.modelMapper.map(restauranteInput, Restaurante.class);
    }*/

    public List<CidadeDTO> toListDTO(List<Cidade> cidades) {
        return cidades.stream().map(this::toDto).collect(Collectors.toList());
    }

}
