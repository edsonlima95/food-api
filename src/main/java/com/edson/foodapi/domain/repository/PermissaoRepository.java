package com.edson.foodapi.domain.repository;

import com.edson.foodapi.domain.model.Grupo;
import com.edson.foodapi.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> { }
