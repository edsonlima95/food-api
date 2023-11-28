package com.edson.foodapi.api.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDTO {

    private Long id;
    private String nome;
    private String taxaFrete;
    private CozinhaDTO cozinha;
    private EnderecoDTO endereco;
    private Boolean ativo;
    private Boolean aberto;

}
