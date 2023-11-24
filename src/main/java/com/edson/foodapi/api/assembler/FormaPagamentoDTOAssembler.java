package com.edson.foodapi.api.assembler;

import com.edson.foodapi.api.model.Inputs.FormaPagamentoInput;
import com.edson.foodapi.api.model.dto.FormaPagamentoDTO;
import com.edson.foodapi.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoDTO toDTO(FormaPagamento formaPagamento){
        return this.modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
    }

    public FormaPagamento toModel(FormaPagamentoInput formaPagamentoInput){
        return this.modelMapper.map(formaPagamentoInput, FormaPagamento.class);
    }

    public void toDomainObject(FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamento){
        this.modelMapper.map(formaPagamentoInput, formaPagamento);
    }

    public List<FormaPagamentoDTO> toListDTO(Collection<FormaPagamento> formaPagamentoList){
        return formaPagamentoList.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
