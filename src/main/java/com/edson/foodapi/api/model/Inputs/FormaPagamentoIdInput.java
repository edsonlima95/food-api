package com.edson.foodapi.api.model.Inputs;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoIdInput {

    @NotNull
    private Long id;

}
