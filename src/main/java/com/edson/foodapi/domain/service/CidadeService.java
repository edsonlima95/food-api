package com.edson.foodapi.domain.service;


import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Cidade;
import com.edson.foodapi.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> listar(){
        return this.cidadeRepository.findAll();
    }

    public Cidade buscarPorId(Long id){
        return this.cidadeRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Cidade não encontrada"));
    }
}

