package com.edson.foodapi.domain.model;


import com.edson.foodapi.domain.validationGroups.ValidationsGroups;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import lombok.Data;

@Data
@Embeddable
public class Endereco {

    @NotBlank
    @Column(name = "endereco_cep")
    private String cep;

    @NotBlank
    @Column(name = "endereco_logradouro")
    private String logradouro;

    @NotBlank
    @Column(name = "endereco_numero")
    private String numero;

    @Column(name = "endereco_complemento")
    private String complemento;

    @NotBlank
    @Column(name = "endereco_bairro")
    private String bairro;

    @Valid
    @NotNull
    @ConvertGroup(to = ValidationsGroups.CidadeId.class)
    @ManyToOne
    @JoinColumn(name = "endereco_cidade_id")
    private Cidade cidade;

}
