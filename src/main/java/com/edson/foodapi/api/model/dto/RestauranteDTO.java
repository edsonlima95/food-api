package com.edson.foodapi.api.model.dto;


import com.edson.foodapi.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDTO {

    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private Long id;

    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private String nome;

    @JsonView(RestauranteView.Resumo.class)
    private String taxaFrete;

    @JsonView(RestauranteView.Resumo.class)
    private CozinhaDTO cozinha;

    private EnderecoDTO endereco;
    private Boolean ativo;
    private Boolean aberto;

}
