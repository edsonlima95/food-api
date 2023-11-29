package com.edson.foodapi.api.assembler;


import com.edson.foodapi.api.model.Inputs.PedidoInput;
import com.edson.foodapi.api.model.dto.PedidoDTO;
import com.edson.foodapi.api.model.dto.PedidoResumoDTO;
import com.edson.foodapi.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public PedidoResumoDTO toResumoDto(Pedido pedido) {
        return this.modelMapper.map(pedido, PedidoResumoDTO.class);
    }

    public void toDomainObject(PedidoInput pedidoInput, Pedido pedido) {
        modelMapper.map(pedidoInput, pedido);
    }

    public List<PedidoDTO> toListDto(List<Pedido> pedidos){
        return pedidos.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<PedidoResumoDTO> toListResumoDto(List<Pedido> pedidos){
        return pedidos.stream().map(this::toResumoDto).collect(Collectors.toList());
    }

}
