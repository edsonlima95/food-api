package com.edson.foodapi.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;

}
