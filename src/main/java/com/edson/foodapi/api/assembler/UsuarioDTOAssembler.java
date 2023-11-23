package com.edson.foodapi.api.assembler;

import com.edson.foodapi.api.model.Inputs.UsuarioInput;
import com.edson.foodapi.api.model.Inputs.UsuarioInputUpdate;
import com.edson.foodapi.api.model.dto.UsuarioDTO;
import com.edson.foodapi.domain.model.Cidade;
import com.edson.foodapi.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDTO toDto(Usuario usuario) {
        return this.modelMapper.map(usuario, UsuarioDTO.class);
    }

    public Usuario toModel(UsuarioInput usuarioInput) {
        return this.modelMapper.map(usuarioInput, Usuario.class);
    }

    public void toModelObject(UsuarioInputUpdate usuarioInputUpdate, Usuario usuario) {
         this.modelMapper.map(usuarioInputUpdate, usuario);
    }

    public Usuario toModelUpdate(UsuarioInputUpdate usuarioInputUpdate) {
        return this.modelMapper.map(usuarioInputUpdate, Usuario.class);
    }

    public List<UsuarioDTO> toListDTO(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toDto).collect(Collectors.toList());
    }

}
