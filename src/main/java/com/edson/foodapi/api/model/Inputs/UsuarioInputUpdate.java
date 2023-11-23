package com.edson.foodapi.api.model.Inputs;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UsuarioInputUpdate {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

}
