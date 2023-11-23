package com.edson.foodapi.api.assembler;

import com.edson.foodapi.api.model.Inputs.RestauranteInput;
import com.edson.foodapi.api.model.dto.CidadeResumoDTO;
import com.edson.foodapi.api.model.dto.RestauranteDTO;
import com.edson.foodapi.domain.model.Cidade;
import com.edson.foodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteDTO toDto(Restaurante restaurante) {

        this.modelMapper.typeMap(Cidade.class, CidadeResumoDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getEstado().getNome(), CidadeResumoDTO::setEstado);
        });

        return this.modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public Restaurante toModel(RestauranteInput restauranteInput) {
        return this.modelMapper.map(restauranteInput, Restaurante.class);
    }

    public List<RestauranteDTO> toListDTO(List<Restaurante> restaurantes) {
        return restaurantes.stream().map(this::toDto).collect(Collectors.toList());
    }

}
