package com.edson.foodapi.api.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoDTO {

    private Long id;
    private String nome;
    private String estado;

}
