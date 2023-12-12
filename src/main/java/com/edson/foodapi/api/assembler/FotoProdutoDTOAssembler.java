package com.edson.foodapi.api.assembler;

import com.edson.foodapi.api.model.dto.FotoProdutoDTO;
import com.edson.foodapi.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoDTO toDto(FotoProduto fotoProduto){
        return this.modelMapper.map(fotoProduto, FotoProdutoDTO.class);
    }

}
