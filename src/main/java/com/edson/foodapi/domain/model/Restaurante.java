package com.edson.foodapi.domain.model;

import com.edson.foodapi.domain.validationGroups.ValidationsGroups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.groups.ConvertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nome;

    @PositiveOrZero
    @Column(name = "taxa_frete", precision = 10, scale = 2)
    private BigDecimal taxaFrete;


    @CreationTimestamp
    @Column(nullable = false)
    private OffsetDateTime dataCriacao;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime dataAtualizacao;

    @Valid //Indica que as propriedades da cozinha tbm tem que ser validadas
    @NotNull
    @ConvertGroup(to = ValidationsGroups.CozinhaId.class)
    @ManyToOne
    private Cozinha cozinha;

    @Valid
    @NotNull
    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamentos",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "pagamento_id")
    )
    private List<FormaPagamento> formaPagamentos = new ArrayList<>();
}
