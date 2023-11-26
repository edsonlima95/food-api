package com.edson.foodapi.api.assembler;


import com.edson.foodapi.api.model.Inputs.PedidoInput;
import com.edson.foodapi.api.model.dto.PedidoDTO;
import com.edson.foodapi.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;


    public Pedido toModel(PedidoInput pedidoInput) {

        return modelMapper.map(pedidoInput, Pedido.class);
    }

    public PedidoDTO toDto(Pedido pedido) {
        return this.modelMapper.map(pedido, PedidoDTO.class);
    }

    public void toDomainObject(PedidoInput pedidoInput, Pedido pedido) {
        modelMapper.map(pedidoInput, pedido);
    }

}
