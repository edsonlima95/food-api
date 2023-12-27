package com.edson.foodapi.api.assembler;


import com.edson.foodapi.api.model.Inputs.UsuarioCredenciaisInput;
import com.edson.foodapi.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioCredenciaisAssembler {


    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainModel(UsuarioCredenciaisInput usuarioCredenciaisInput) {
        return modelMapper.map(usuarioCredenciaisInput, Usuario.class);
    }

}
