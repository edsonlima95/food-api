package com.edson.foodapi.api.model.Inputs;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInput {

    private Long id;

    @NotBlank
    private String nome;

}
