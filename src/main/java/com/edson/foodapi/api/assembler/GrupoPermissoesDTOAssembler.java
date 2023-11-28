package com.edson.foodapi.api.assembler;

import com.edson.foodapi.api.model.Inputs.GrupoInput;
import com.edson.foodapi.api.model.Inputs.GrupoPermissoesInput;
import com.edson.foodapi.api.model.dto.GrupoDTO;
import com.edson.foodapi.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoPermissoesDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;
    public void toDomainObject(GrupoPermissoesInput grupoPermissoesInput, Grupo grupo) {
         this.modelMapper.map(grupoPermissoesInput, grupo);
    }


}
