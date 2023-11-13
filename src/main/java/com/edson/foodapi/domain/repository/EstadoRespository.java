package com.edson.foodapi.domain.repository;

import com.edson.foodapi.domain.model.Cozinha;
import com.edson.foodapi.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRespository extends JpaRepository<Estado, Long> { }
