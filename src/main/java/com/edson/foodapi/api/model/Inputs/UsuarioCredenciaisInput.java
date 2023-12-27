package com.edson.foodapi.api.model.Inputs;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCredenciaisInput {

    @NotBlank
    @Email
    private String email;

    private String senha;

}
