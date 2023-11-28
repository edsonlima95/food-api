package com.edson.foodapi.domain.service;


import com.edson.foodapi.domain.exception.NotFoundException;
import com.edson.foodapi.domain.model.Permissao;
import com.edson.foodapi.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;


    public Permissao buscarPorId(Long id){
        return this.permissaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

}
