package com.edson.foodapi.domain.service;


import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Cidade;
import com.edson.foodapi.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade buscarPorId(Long id){
        return this.cidadeRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Cidade não encontrada"));
    }
}

