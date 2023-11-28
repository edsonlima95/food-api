package com.edson.foodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    private String email;

    private String senha;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id")
    )
    private List<Grupo> grupos = new ArrayList<>();

    public boolean verificaSenha(String senha) {
        return this.getSenha().equals(senha);
    }
}
