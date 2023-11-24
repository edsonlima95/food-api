package com.edson.foodapi.domain.repository;

import com.edson.foodapi.domain.model.Cidade;
import com.edson.foodapi.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> { }
