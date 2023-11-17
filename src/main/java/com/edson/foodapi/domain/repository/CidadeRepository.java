package com.edson.foodapi.domain.repository;

import com.edson.foodapi.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> { }
