package com.edson.foodapi.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    private BigDecimal taxaFrete;

    private Boolean ativo = Boolean.TRUE;

    private Boolean aberto = Boolean.TRUE;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;

    /*@Valid //Indica que as propriedades da cozinha tbm tem que ser validadas
    @NotNull
    @ConvertGroup(to = ValidationsGroups.CozinhaId.class)*/
    @ManyToOne
    private Cozinha cozinha;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamentos",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "pagamento_id")
    )
    private Set<FormaPagamento> formaPagamentos = new HashSet<>();

    public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
        return this.getFormaPagamentos().contains(formaPagamento);
    }

    public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
        return !aceitaFormaPagamento(formaPagamento);
    }
}
