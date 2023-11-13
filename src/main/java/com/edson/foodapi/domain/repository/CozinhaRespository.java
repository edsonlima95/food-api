package com.edson.foodapi.domain.repository;

import com.edson.foodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CozinhaRespository extends JpaRepository<Cozinha, Long> {

    List<Cozinha> findByNome(String nome);

}
