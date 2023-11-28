package com.edson.foodapi.api.assembler;

import com.edson.foodapi.api.model.Inputs.ProdutoInput;
import com.edson.foodapi.api.model.dto.ProdutoDTO;
import com.edson.foodapi.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toModel(ProdutoInput produtoInput) {
        return this.modelMapper.map(produtoInput, Produto.class);
    }

    public void toDomainObject(ProdutoInput produtoInput, Produto produto) {
         this.modelMapper.map(produtoInput, produto);
    }

    public ProdutoDTO toDto(Produto produto) {
        return this.modelMapper.map(produto, ProdutoDTO.class);
    }

    public List<ProdutoDTO> toListDTO(List<Produto> produtos) {
        return produtos.stream().map(this::toDto).collect(Collectors.toList());
    }

}
