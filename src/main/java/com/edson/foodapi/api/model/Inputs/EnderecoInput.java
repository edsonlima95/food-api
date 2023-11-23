package com.edson.foodapi.api.model.Inputs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

    @NotNull
    private String cep;
    @NotNull
    private String logradouro;
    @NotNull
    private String numero;
    @NotNull
    private String bairro;

    @Valid
    @NotNull
    private CidadeIdInput cidade;

}
