package com.edson.foodapi.api.assembler;

import com.edson.foodapi.api.core.modelmapper.ModelMapperConfig;
import com.edson.foodapi.api.model.CidadeDTO;
import com.edson.foodapi.api.model.EnderecoDTO;
import com.edson.foodapi.api.model.EstadoDTO;
import com.edson.foodapi.api.model.RestauranteDTO;
import com.edson.foodapi.domain.model.Cidade;
import com.edson.foodapi.domain.model.Endereco;
import com.edson.foodapi.domain.model.Estado;
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

       /* this.modelMapper.typeMap(Endereco.class, EnderecoDTO.class).addMappings(mapper -> {
            mapper.map(Endereco::getLogradouro, EnderecoDTO::setRua);
        });*/

        this.modelMapper.typeMap(Cidade.class, CidadeDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getEstado().getNome(), CidadeDTO::setEstado);
        });

        return this.modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public List<RestauranteDTO> toListDTO(List<Restaurante> restaurantes) {
        return restaurantes.stream().map(this::toDto).collect(Collectors.toList());
    }

}
