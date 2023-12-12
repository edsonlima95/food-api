package com.edson.foodapi.api.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoDTO {

    private String nome;
    private String descricao;
    private String contentType;
    private Long tamanho;

}
