package com.edson.foodapi.domain.service;


import com.edson.foodapi.domain.model.Cozinha;
import com.edson.foodapi.domain.model.Estado;
import com.edson.foodapi.domain.repository.CozinhaRespository;
import com.edson.foodapi.domain.repository.EstadoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRespository estadoRespository;


    public List<Estado> listar(){
        return this.estadoRespository.findAll();
    }

}
