package com.edson.foodapi.domain.model;


import com.edson.foodapi.domain.validationGroups.ValidationsGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NotNull(groups = ValidationsGroups.CidadeId.class)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne
    private Estado estado;

}
