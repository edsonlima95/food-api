package com.edson.foodapi.api.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDTO {

    private Long id;
    private String taxaFrete;
    private CozinhaDTO cozinha;
    private EnderecoDTO endereco;

}
