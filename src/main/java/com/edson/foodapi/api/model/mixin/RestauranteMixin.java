package com.edson.foodapi.api.model.mixin;

import com.edson.foodapi.domain.model.Cozinha;
import com.edson.foodapi.domain.model.Endereco;
import com.edson.foodapi.domain.model.FormaPagamento;
import com.edson.foodapi.domain.model.Produto;
import com.edson.foodapi.domain.validationGroups.ValidationsGroups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime dataCriacao;

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    @JsonIgnore
    private List<FormaPagamento> formaPagamentos = new ArrayList<>();

}
