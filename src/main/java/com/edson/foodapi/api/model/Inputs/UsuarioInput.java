package com.edson.foodapi.api.model.Inputs;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
public class UsuarioInput extends UsuarioInputUpdate {

    @NotBlank
    private String senha;

}
