package com.edson.foodapi.api.model.Inputs;

import com.edson.foodapi.domain.model.Restaurante;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

    @NotBlank
    private String nome;

    private String descricao;

    @PositiveOrZero
    @NotNull
    private String preco;

    @NotNull
    private Boolean ativo;

    @Valid
    @NotNull
    private RestauranteIdInput restaurante;

}
