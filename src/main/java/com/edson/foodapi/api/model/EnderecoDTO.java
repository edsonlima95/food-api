package com.edson.foodapi.api.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private CidadeDTO cidade;
}
